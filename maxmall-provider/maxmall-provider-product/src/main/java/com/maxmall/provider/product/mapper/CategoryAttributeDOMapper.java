package com.maxmall.provider.product.mapper;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.product.model.domain.CategoryAttributeDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table PMS_CATEGORY_ATTRIBUTE.
 * 类目销售属性表
 */
@Mapper
@Component
public interface CategoryAttributeDOMapper extends MyMapper<CategoryAttributeDO> {


}
