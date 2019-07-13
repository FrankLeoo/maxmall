package com.maxmall.provider.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.core.enums.CommonStatusEnum;
import com.maxmall.common.core.support.BaseService;
import com.maxmall.common.core.utils.PermissionCheckUtil;
import com.maxmall.common.util.converter.BeanConverter;
import com.maxmall.provider.product.exception.ProductBizException;
import com.maxmall.provider.product.mapper.CategoryAttributeDOMapper;
import com.maxmall.provider.product.mapper.ProductCategoryDOMapper;
import com.maxmall.provider.product.model.domain.CategoryAttributeDO;
import com.maxmall.provider.product.model.domain.ProductCategoryDO;
import com.maxmall.provider.product.model.dto.CategoryModifyDto;
import com.maxmall.provider.product.model.dto.CategoryQueryDto;
import com.maxmall.provider.product.model.vo.CategoryAttributeVo;
import com.maxmall.provider.product.model.vo.CategoryVo;
import com.maxmall.provider.product.service.CategoryService;
import com.maxmall.provider.product.utils.CategoryTreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ivoter
 * @ClassName CategoryServiceImpl.java
 * @date 2019/05/22 10:26:00
 * @Description 商品分类service
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class CategoryServiceImpl extends BaseService<ProductCategoryDO> implements CategoryService {

    @Autowired
    private ProductCategoryDOMapper productCategoryDOMapper;
    @Autowired
    private CategoryAttributeDOMapper categoryAttributeDOMapper;

    @Override
    public List<CategoryVo> listAllCategory(UserTokenDto loginAuthDto) {
        ProductCategoryDO param = new ProductCategoryDO();
        param.setMerchantId(loginAuthDto.getMerchantId());
        param.setStatus(CommonStatusEnum.ENABLE.getStatus());

        List<ProductCategoryDO> categoryDOList = productCategoryDOMapper.select(param);
        List<CategoryVo> result = BeanConverter.batchConvert(categoryDOList,CategoryVo.class);

        return result;
    }

    @Override
    public PageInfo<CategoryVo> queryCategoryListWithPage(CategoryQueryDto queryParam, UserTokenDto loginAuthDto) {
        Integer pageNum = queryParam.getPageNum();

        Integer pageSize = queryParam.getPageSize();
        if (pageNum ==null || pageNum <=0){
            pageNum = 1;
        }
        if (pageSize ==null || pageSize <=0 || pageSize>100){
            pageSize = 20;
        }
        PageHelper.startPage(pageNum, pageSize);
        //查询参数
        Example example = new Example(ProductCategoryDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andEqualTo("status", CommonStatusEnum.ENABLE.getStatus());

        if (StringUtils.isNotEmpty(queryParam.getName())){
            criteria.andLike("name","%"+queryParam.getName()+"%");
        }
        List<ProductCategoryDO> doList = productCategoryDOMapper.selectByExample(example);
        PageInfo<ProductCategoryDO> pageInfo = new PageInfo<>(doList);

        //转化
        List<CategoryVo> responses = BeanConverter.batchConvert(doList,CategoryVo.class);
        PageInfo<CategoryVo> result = BeanConverter.convert(pageInfo,PageInfo.class);
        result.setList(responses);

        return result;
    }

    @Override
    public int saveOrModifyCategory(CategoryModifyDto categoryDto, UserTokenDto loginAuthDto) {
        Long id = categoryDto.getId();
        ProductCategoryDO categoryEntity = BeanConverter.convert(categoryDto,ProductCategoryDO.class);
        categoryEntity.setUpdateInfo(loginAuthDto);

        Long parentId = categoryDto.getParentId();
        if (parentId != null && parentId > 0){

            ProductCategoryDO parent = productCategoryDOMapper.selectByPrimaryKey(parentId);
            if (parent != null){
                categoryEntity.setParentId(parent.getId());
                categoryEntity.setParentIds(parent.getParentIds()+parent.getId()+ GlobalConstant.Symbol.COMMA);
                categoryEntity.setLevel(parent.getLevel()+1);
            }else{
                categoryEntity.setParentId(0L);
                categoryEntity.setLevel(0);
            }
        }else{
            categoryEntity.setParentId(0L);
            categoryEntity.setLevel(0);
        }

        int result = 0;
        if (id != null){
            ProductCategoryDO categoryDO = productCategoryDOMapper.selectByPrimaryKey(id);
            if (categoryDO == null){
                throw new ProductBizException(ErrorCodeEnum.MDC10023001,id);
            }
            PermissionCheckUtil.checkDataMerchantPermission(categoryDO.getMerchantId(),loginAuthDto);
            result = productCategoryDOMapper.updateByPrimaryKeySelective(categoryEntity);

            //删除类目属性
            CategoryAttributeDO param = new CategoryAttributeDO();
            param.setCategoryId(id);
            categoryAttributeDOMapper.delete(param);
        }else{
            categoryEntity.setMerchantId(loginAuthDto.getMerchantId());
            categoryEntity.setStatus(CommonStatusEnum.ENABLE.getStatus());

            result =productCategoryDOMapper.insert(categoryEntity);
        }
        //保存类目属性
        if (CollectionUtils.isNotEmpty(categoryDto.getAttributeList())){
            List<CategoryAttributeDO> attributeDOS = categoryDto.getAttributeList().stream().map(item -> {
                CategoryAttributeDO attributeDO = BeanConverter.convert(item, CategoryAttributeDO.class);
                attributeDO.setUpdateInfo(loginAuthDto);
                attributeDO.setCategoryId(categoryEntity.getId());
                attributeDO.setMerchantId(categoryEntity.getMerchantId());

                return attributeDO;
            }).collect(Collectors.toList());

            categoryAttributeDOMapper.insertList(attributeDOS);
        }
        return result;

    }

    @Override
    public int deleteCategory(Long id, UserTokenDto loginAuthDto) {
        ProductCategoryDO categoryDO = productCategoryDOMapper.selectByPrimaryKey(id);
        if (categoryDO == null){
            throw new ProductBizException(ErrorCodeEnum.MDC10023001,id);
        }
        PermissionCheckUtil.checkDataMerchantPermission(categoryDO.getMerchantId(),loginAuthDto);

        //删除所有子类
        String parentIds = categoryDO.getParentIds();
        parentIds += categoryDO.getId()+",";

        Example example = new Example(ProductCategoryDO.class);
        example.or().andEqualTo("id",categoryDO.getId());
        example.or().andLike("parentIds",parentIds+"%");

        ProductCategoryDO updateDO = new ProductCategoryDO();
        updateDO.setStatus(CommonStatusEnum.DISABLE.getStatus());
        updateDO.setUpdateInfo(loginAuthDto);
        return productCategoryDOMapper.updateByExampleSelective(updateDO,example);
    }

    /**
     * 分类详情
     * @param id
     * @param loginAuthDto
     * @return
     */
    @Override
    public CategoryVo categoryDetail(Long id, UserTokenDto loginAuthDto) {
        ProductCategoryDO categoryDO = productCategoryDOMapper.selectByPrimaryKey(id);
        if (categoryDO == null){
            throw new ProductBizException(ErrorCodeEnum.MDC10023001,id);
        }
        PermissionCheckUtil.checkDataMerchantPermission(categoryDO.getMerchantId(),loginAuthDto);
        CategoryVo result = BeanConverter.convert(categoryDO,CategoryVo.class);
        if (StringUtils.isNotEmpty(result.getParentIds())){
            String[] parentIdsList= result.getParentIds().split(",");
            result.setParentIdsList(parentIdsList);
        }else{
            result.setParentIdsList(new String[]{});
        }

        //查询属性
        CategoryAttributeDO param = new CategoryAttributeDO();
        param.setCategoryId(categoryDO.getId());
        List<CategoryAttributeDO> attributeDOS = categoryAttributeDOMapper.select(param);

        if (CollectionUtils.isNotEmpty(attributeDOS)){
            List<CategoryAttributeVo> attributeList = BeanConverter.batchConvert(attributeDOS,CategoryAttributeVo.class);
            result.setAttributeList(attributeList);
        }

        return result;
    }

    /**
     * 更新显示状态
     * @param ids
     * @param showStatus
     * @return
     */
    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus,UserTokenDto loginAuthDto) {
        ProductCategoryDO categoryDO = new ProductCategoryDO();
        categoryDO.setShowStatus(showStatus);

        Example example = new Example(ProductCategoryDO.class);
        example.createCriteria().andIn("id",ids);

        List<ProductCategoryDO> categoryDOList = productCategoryDOMapper.selectByExample(example);
        categoryDOList.forEach(item -> {
            PermissionCheckUtil.checkDataMerchantPermission(item.getMerchantId(),loginAuthDto);
        });

        return productCategoryDOMapper.updateByExampleSelective(categoryDO, example);
    }

    /**
     * 获取树列表
     * @param loginAuthDto
     * @return
     */
    @Override
    public List<CategoryVo> getTreeCategory(UserTokenDto loginAuthDto,CategoryQueryDto queryParam) {

        //查询参数
        Example example = new Example(ProductCategoryDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andEqualTo("parentId",queryParam.getParentId())
                .andEqualTo("status", CommonStatusEnum.ENABLE.getStatus());

        Long parentId = queryParam.getParentId();
        if (parentId == null){
            parentId = 0L;
        }
        boolean isNameQuery = false;
        if (StringUtils.isNotEmpty(queryParam.getName())){
            criteria.andLike("name","%"+queryParam.getName()+"%");
            isNameQuery = true;
        }
        List<ProductCategoryDO> doList = productCategoryDOMapper.selectByExample(example);

        List<CategoryVo> categoryVos = doList.stream().map(item -> {
            CategoryVo categoryVo = BeanConverter.convert(item, CategoryVo.class);
            //查询属性
            CategoryAttributeDO param = new CategoryAttributeDO();
            param.setCategoryId(item.getId());
            List<CategoryAttributeDO> attributeDOS = categoryAttributeDOMapper.select(param);

            if (CollectionUtils.isNotEmpty(attributeDOS)){
                List<CategoryAttributeVo> attributeList = BeanConverter.batchConvert(attributeDOS,CategoryAttributeVo.class);
                categoryVo.setAttributeList(attributeList);
            }
            return categoryVo;
        }).collect(Collectors.toList());

        if (isNameQuery){
            return categoryVos;
        }
        List<CategoryVo> treeVos = CategoryTreeUtil.getChildCategoryVos(categoryVos, parentId);
        return treeVos;
    }
}
