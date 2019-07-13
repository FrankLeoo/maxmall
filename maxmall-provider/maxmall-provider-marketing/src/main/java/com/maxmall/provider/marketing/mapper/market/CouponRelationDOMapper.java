package com.maxmall.provider.marketing.mapper.market;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.marketing.model.domain.market.CouponRelationDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table SMS_COUPON_RELATION.
 * 优惠券的关系表
 */
@Mapper
@Component
public interface CouponRelationDOMapper extends MyMapper<CouponRelationDO> {


}
