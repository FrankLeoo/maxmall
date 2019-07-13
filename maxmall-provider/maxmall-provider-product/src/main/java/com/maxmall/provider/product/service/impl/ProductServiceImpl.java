package com.maxmall.provider.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.core.enums.CommonStatusEnum;
import com.maxmall.common.core.enums.TrueOrFalseEnum;
import com.maxmall.common.core.utils.DateUtil;
import com.maxmall.common.core.utils.PermissionCheckUtil;
import com.maxmall.common.util.converter.BeanConverter;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.product.exception.ProductBizException;
import com.maxmall.provider.product.mapper.BrandDOMapper;
import com.maxmall.provider.product.mapper.ProductCategoryDOMapper;
import com.maxmall.provider.product.mapper.ProductSkuDOMapper;
import com.maxmall.provider.product.mapper.ProductSpuDOMapper;
import com.maxmall.provider.product.model.domain.BrandDO;
import com.maxmall.provider.product.model.domain.ProductCategoryDO;
import com.maxmall.provider.product.model.domain.ProductSkuDO;
import com.maxmall.provider.product.model.domain.ProductSpuDO;
import com.maxmall.provider.product.model.domain.espojo.EsProduct;
import com.maxmall.provider.product.model.dto.*;
import com.maxmall.provider.product.model.enums.ProductLabelEnum;
import com.maxmall.provider.product.model.vo.ProductSkuVo;
import com.maxmall.provider.product.model.vo.ProductVo;
import com.maxmall.provider.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductSpuDOMapper productSpuDOMapper;
    @Autowired
    private ProductSkuDOMapper productSkuDOMapper;
    @Autowired
    private BrandDOMapper brandDOMapper;
    @Autowired
    private ProductCategoryDOMapper productCategoryDOMapper;

    /**
     * 分页查询商品信息
     * @param queryParam
     * @param loginAuthDto
     * @return
     */
    @Override
    public PageInfo<ProductVo> queryProductListWithPage(ProductQueryDto queryParam, UserTokenDto loginAuthDto) {
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
        Example example = new Example(ProductSpuDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andEqualTo("status", CommonStatusEnum.ENABLE.getStatus()).andEqualTo("brandId",queryParam.getBrandId())
                .andEqualTo("categoryId",queryParam.getCategoryId());

        if (StringUtils.isNotEmpty(queryParam.getName())){
            criteria.andLike("name","%"+queryParam.getName()+"%");
        }
        if (StringUtils.isNotEmpty(queryParam.getSpuCode())){
            criteria.andLike("spuCode","%"+queryParam.getSpuCode()+"%");
        }
        List<ProductSpuDO> doList = productSpuDOMapper.selectByExample(example);
        PageInfo<ProductSpuDO> pageInfo = new PageInfo<>(doList);

        //转化
        List<ProductVo> responses = BeanConverter.batchConvert(doList,ProductVo.class);
        PageInfo<ProductVo> result = BeanConverter.convert(pageInfo,PageInfo.class);
        result.setList(responses);

        return result;
    }

    /**
     * 获取商品详情
     * @param id
     * @param loginAuthDto
     * @return
     */
    @Override
    public ProductVo productDetail(Long id, UserTokenDto loginAuthDto) {

        ProductSpuDO spuDO = productSpuDOMapper.selectByPrimaryKey(id);
        if (spuDO == null){
            throw new ProductBizException(ErrorCodeEnum.MDC10021004,id);
        }
        PermissionCheckUtil.checkDataMerchantPermission(spuDO.getMerchantId(),loginAuthDto);

        ProductVo result = BeanConverter.convert(spuDO,ProductVo.class);
        //查询sku 列表
        ProductSkuDO param = new ProductSkuDO();
        param.setSpuId(spuDO.getId());
        List<ProductSkuDO> skuDOList = productSkuDOMapper.select(param);

        if (CollectionUtils.isNotEmpty(skuDOList)){
            List<ProductSkuVo> skuVoList = BeanConverter.batchConvert(skuDOList,ProductSkuVo.class);
            result.setSkuList(skuVoList);
        }
        return result;
    }

    @Override
    public int saveOrModifyProduct(ProductModifyDto modifyDto, UserTokenDto loginAuthDto) {

        Long id = modifyDto.getId();
        ProductSpuDO spuEntity = BeanConverter.convert(modifyDto,ProductSpuDO.class);
        spuEntity.setUpdateInfo(loginAuthDto);
        //参数转化 品牌 分类
        convertEntityBrandCategoryParam(spuEntity);

        int result = 0;
        if (id != null){
            ProductSpuDO spuDO = productSpuDOMapper.selectByPrimaryKey(id);
            if (spuDO == null){
                throw new ProductBizException(ErrorCodeEnum.MDC10021004,id);
            }
            PermissionCheckUtil.checkDataMerchantPermission(spuDO.getMerchantId(),loginAuthDto);
            result = productSpuDOMapper.updateByPrimaryKeySelective(spuEntity);

            //删除sku
            ProductSkuDO param = new ProductSkuDO();
            param.setSpuId(id);
            productSkuDOMapper.delete(param);
        }else{
            spuEntity.setMerchantId(loginAuthDto.getMerchantId());
            spuEntity.setStatus(CommonStatusEnum.ENABLE.getStatus());
            //规则配置如下：3位商户+年月日时分秒 时间戳
            String spuCode = String.format("%03d", loginAuthDto.getMerchantId())+ DateUtil.formatYMDHMS();
            spuEntity.setSpuCode(spuCode);

            result =productSpuDOMapper.insert(spuEntity);
        }
        //添加sku
        if (CollectionUtils.isNotEmpty(modifyDto.getSkuList())){
            AtomicInteger index = new AtomicInteger();
            List<ProductSkuDO> skuDOS = modifyDto.getSkuList().stream().map(item -> {
                index.getAndIncrement();

                ProductSkuDO skuDO = BeanConverter.convert(item, ProductSkuDO.class);
                skuDO.setMerchantId(loginAuthDto.getMerchantId());
                skuDO.setSpuId(spuEntity.getId());
                skuDO.setStatus(CommonStatusEnum.ENABLE.getStatus());
                skuDO.setSaleNum(0);
                String skuCode = DateUtil.formatYMD() + String.format("%04d", spuEntity.getId()) + String.format("%03d", index);
                skuDO.setSkuCode(skuCode);

                return skuDO;
            }).collect(Collectors.toList());

            productSkuDOMapper.insertList(skuDOS);
        }
        //数据处理调用es存储

        return result;
    }

    @Override
    public List<EsProduct> getAllEsProductList(Long id) {

        return null;
    }

    /**
     * 根据id查询商品list
     * @param productIds
     * @param merchantId
     * @return
     */
    @Override
    public Wrapper<List<ProductDto>> selectByIds(List<Long> productIds, Long merchantId) {
        Example example = new Example(ProductSpuDO.class);
        example.createCriteria().andEqualTo("merchantId",merchantId).andEqualTo("status",CommonStatusEnum.ENABLE.getStatus())
                .andEqualTo("isPublish", TrueOrFalseEnum.TRUE.getStatus()).andIn("id",productIds);

        List<ProductSpuDO> spuDOList = productSpuDOMapper.selectByExample(example);
        List<ProductDto> result = BeanConverter.batchConvert(spuDOList,ProductDto.class);

        return WrapMapper.ok(result);
    }

    @Override
    public int deleteByIds(ArrayList<Long> ids, UserTokenDto loginAuthDto) {

        if (CollectionUtils.isEmpty(ids)){
            return 0;
        }
        Example example = new Example(ProductSpuDO.class);
        example.createCriteria().andIn("id",ids);
        List<ProductSpuDO> reasonDOList = productSpuDOMapper.selectByExample(example);

        //验证
        reasonDOList.stream().forEach(item -> PermissionCheckUtil.checkDataMerchantPermission(item.getMerchantId(),loginAuthDto));

        ProductSpuDO spuDO = new ProductSpuDO();
        spuDO.setStatus(CommonStatusEnum.DISABLE.getStatus());
        spuDO.setUpdateInfo(loginAuthDto);
        int result = productSpuDOMapper.updateByExampleSelective(spuDO,example);

        //删除spu
        ProductSkuDO skuDO = new ProductSkuDO();
        skuDO.setStatus(CommonStatusEnum.DISABLE.getStatus());
        skuDO.setUpdateInfo(loginAuthDto);

        Example skuExample = new Example(ProductSkuDO.class);
        skuExample.createCriteria().andIn("spuId",ids);

        productSkuDOMapper.updateByExampleSelective(skuDO,skuExample);
        return result;
    }

    @Override
    public int modifyProductLabel(ProductLabelModifyDto modifyDto, UserTokenDto loginAuthDto) {
        ProductSpuDO spuDO = productSpuDOMapper.selectByPrimaryKey(modifyDto.getId());
        if (spuDO == null){
            throw new ProductBizException(ErrorCodeEnum.MDC10021004,modifyDto.getId());
        }
        PermissionCheckUtil.checkDataMerchantPermission(spuDO.getMerchantId(),loginAuthDto);

        ProductLabelEnum labelEnum = ProductLabelEnum.getByType(modifyDto.getType());
        if (labelEnum == null){
            return 0;
        }
        ProductSpuDO updateDO = new ProductSpuDO();
        updateDO.setId(modifyDto.getId());
        switch (labelEnum){
            case HOT:
                updateDO.setHotStatus(modifyDto.getStatus());
                break;
            case NEW:
                updateDO.setNewStatus(modifyDto.getStatus());
                break;
            case RECOMMAND:
                updateDO.setRecommandStatus(modifyDto.getStatus());
                break;
        }

        return productSpuDOMapper.updateByPrimaryKeySelective(updateDO);
    }

    @Override
    public List<ProductVo> productListAll(UserTokenDto loginAuthDto, ProductListQueryDto queryParam) {

        Example example = new Example(ProductSpuDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andEqualTo("status", CommonStatusEnum.ENABLE.getStatus()).andEqualTo("brandId",queryParam.getBrandId())
                .andEqualTo("categoryId",queryParam.getCategoryId());

        if (StringUtils.isNotEmpty(queryParam.getName())){
            criteria.andLike("name","%"+queryParam.getName()+"%");
        }
        if (StringUtils.isNotEmpty(queryParam.getSpuCode())){
            criteria.andLike("spuCode","%"+queryParam.getSpuCode()+"%");
        }
        if (StringUtils.isNotEmpty(queryParam.getKeyWords())){
            Example.Criteria orCriteria = example.createCriteria();
            orCriteria.orLike("name","%"+queryParam.getKeyWords()+"%")
                    .orLike("spuCode","%"+queryParam.getKeyWords()+"%");
            example.and(orCriteria);
        }
        List<ProductSpuDO> doList = productSpuDOMapper.selectByExample(example);
        //转化
        List<ProductVo> responses = BeanConverter.batchConvert(doList,ProductVo.class);

        return responses;
    }

    /**
     * 封装参数
     * @param spuEntity
     */
    private void convertEntityBrandCategoryParam(ProductSpuDO spuEntity) {
        Long brandId = spuEntity.getBrandId();
        if (brandId != null){
            BrandDO brandDO = brandDOMapper.selectByPrimaryKey(brandId);
            if (brandDO != null){
                spuEntity.setBrandName(brandDO.getName());
            }
        }
        Long categoryId = spuEntity.getCategoryId();
        if (categoryId != null){
            ProductCategoryDO categoryDO = productCategoryDOMapper.selectByPrimaryKey(categoryId);
            if (categoryDO != null){
                spuEntity.setCategoryName(categoryDO.getName());
            }
        }
    }
}
