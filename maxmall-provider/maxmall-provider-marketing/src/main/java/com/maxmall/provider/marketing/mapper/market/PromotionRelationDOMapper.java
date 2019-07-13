package com.maxmall.provider.marketing.mapper.market;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.marketing.model.domain.market.PromotionRelationDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table SMS_PROMOTION_RELATION.
 * 优惠活动的关系表
 */
@Mapper
@Component
public interface PromotionRelationDOMapper extends MyMapper<PromotionRelationDO> {


}
