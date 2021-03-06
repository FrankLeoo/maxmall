package com.maxmall.provider.marketing.mapper.market;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.marketing.model.domain.market.MemberCouponDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table SMS_MEMBER_COUPON.
 * 会员优惠卷表
 */
@Mapper
@Component
public interface MemberCouponDOMapper extends MyMapper<MemberCouponDO> {


}
