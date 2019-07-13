package com.maxmall.provider.product.mapper;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.product.model.domain.ProductSpuDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table PMS_PRODUCT_SPU.
 * 商品spu表
 */
@Mapper
@Component
public interface ProductSpuDOMapper extends MyMapper<ProductSpuDO> {


}
