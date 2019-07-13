package com.maxmall.provider.product.mapper;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.product.model.domain.ProductSkuDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table PMS_PRODUCT_SKU.
 * 商品sku
 */
@Mapper
@Component
public interface ProductSkuDOMapper extends MyMapper<ProductSkuDO> {


}
