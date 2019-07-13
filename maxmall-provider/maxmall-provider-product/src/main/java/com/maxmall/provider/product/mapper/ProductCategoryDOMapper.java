package com.maxmall.provider.product.mapper;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.product.model.domain.ProductCategoryDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table PMS_PRODUCT_CATEGORY.
 * 商品分类
 */
@Mapper
@Component
public interface ProductCategoryDOMapper extends MyMapper<ProductCategoryDO> {


}
