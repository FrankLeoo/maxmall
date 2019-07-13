package com.maxmall.provider.product.mapper;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.product.model.domain.BrandDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table PMS_BRAND.
 * 商品品牌表
 */
@Mapper
@Component
public interface BrandDOMapper extends MyMapper<BrandDO> {


}
