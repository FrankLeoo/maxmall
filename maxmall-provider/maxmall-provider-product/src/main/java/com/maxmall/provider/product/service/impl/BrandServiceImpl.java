package com.maxmall.provider.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.core.enums.CommonStatusEnum;
import com.maxmall.common.core.support.BaseService;
import com.maxmall.common.core.utils.PermissionCheckUtil;
import com.maxmall.common.util.converter.BeanConverter;
import com.maxmall.provider.product.exception.ProductBizException;
import com.maxmall.provider.product.mapper.BrandDOMapper;
import com.maxmall.provider.product.mapper.ProductSpuDOMapper;
import com.maxmall.provider.product.model.domain.BrandDO;
import com.maxmall.provider.product.model.domain.ProductSpuDO;
import com.maxmall.provider.product.model.dto.BrandModifyDto;
import com.maxmall.provider.product.model.dto.BrandQueryDto;
import com.maxmall.provider.product.model.vo.BrandVo;
import com.maxmall.provider.product.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ivoter
 * @ClassName MemberServiceImpl.java
 * @date 2019/05/22 10:26:00
 * @Description 会员service
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class BrandServiceImpl extends BaseService<BrandDO> implements BrandService {

    @Autowired
    private BrandDOMapper brandDOMapper;
    @Autowired
    private ProductSpuDOMapper productSpuDOMapper;

    @Override
    public List<BrandVo> listAllBrand(UserTokenDto loginAuthDto) {
        BrandDO param = new BrandDO();
        param.setMerchantId(loginAuthDto.getMerchantId());
        param.setStatus(CommonStatusEnum.ENABLE.getStatus());

        List<BrandDO> brandDOList = brandDOMapper.select(param);
        List<BrandVo> result = BeanConverter.batchConvert(brandDOList,BrandVo.class);

        return result;
    }

    @Override
    public PageInfo<BrandVo> queryBrandListWithPage(BrandQueryDto queryParam, UserTokenDto loginAuthDto) {
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
        Example example = new Example(BrandDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andEqualTo("status", CommonStatusEnum.ENABLE.getStatus());

        if (StringUtils.isNotEmpty(queryParam.getName())){
            criteria.andLike("name","%"+queryParam.getName()+"%");
        }
        if (StringUtils.isNotEmpty(queryParam.getEnName())){
            criteria.andLike("enName","%"+queryParam.getEnName()+"%");
        }
        List<BrandDO> doList = brandDOMapper.selectByExample(example);
        PageInfo<BrandDO> pageInfo = new PageInfo<>(doList);

        //转化
        List<BrandVo> responses = BeanConverter.batchConvert(doList,BrandVo.class);
        PageInfo<BrandVo> result = BeanConverter.convert(pageInfo,PageInfo.class);
        result.setList(responses);

        return result;
    }

    @Override
    public int saveOrModifyBrand(BrandModifyDto brandDto, UserTokenDto loginAuthDto) {
        Long id = brandDto.getId();
        BrandDO brandEntity = BeanConverter.convert(brandDto,BrandDO.class);
        brandEntity.setUpdateInfo(loginAuthDto);

        int result = 0;
        if (id != null){
            BrandDO brandDO = brandDOMapper.selectByPrimaryKey(id);
            if (brandDO == null){
                throw new ProductBizException(ErrorCodeEnum.MDC10021004,id);
            }
            PermissionCheckUtil.checkDataMerchantPermission(brandDO.getMerchantId(),loginAuthDto);
            result = brandDOMapper.updateByPrimaryKeySelective(brandEntity);

            // 更新商品品牌
            Example example = new Example(ProductSpuDO.class);
            example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId()).andEqualTo("brandId",id);
            ProductSpuDO spuDO = new ProductSpuDO();
            spuDO.setBrandName(brandEntity.getName());

            productSpuDOMapper.updateByExampleSelective(spuDO,example);
        }else{
            brandEntity.setMerchantId(loginAuthDto.getMerchantId());
            brandEntity.setStatus(CommonStatusEnum.ENABLE.getStatus());

            result =brandDOMapper.insert(brandEntity);
        }
        return result;

    }

    @Override
    public int deleteBrand(Long id, UserTokenDto loginAuthDto) {
        BrandDO brandDO = brandDOMapper.selectByPrimaryKey(id);
        if (brandDO == null){
            throw new ProductBizException(ErrorCodeEnum.MDC10021004,id);
        }
        PermissionCheckUtil.checkDataMerchantPermission(brandDO.getMerchantId(),loginAuthDto);

        BrandDO updateDO = new BrandDO();
        updateDO.setId(id);
        updateDO.setStatus(CommonStatusEnum.DISABLE.getStatus());
        updateDO.setUpdateInfo(loginAuthDto);
        return brandDOMapper.updateByPrimaryKeySelective(updateDO);
    }

    /**
     * 品牌详情
     * @param id
     * @param loginAuthDto
     * @return
     */
    @Override
    public BrandVo brandDetail(Long id, UserTokenDto loginAuthDto) {
        BrandDO brandDO = brandDOMapper.selectByPrimaryKey(id);
        if (brandDO == null){
            throw new ProductBizException(ErrorCodeEnum.MDC10021004,id);
        }
        PermissionCheckUtil.checkDataMerchantPermission(brandDO.getMerchantId(),loginAuthDto);

        return BeanConverter.convert(brandDO,BrandVo.class);
    }
}
