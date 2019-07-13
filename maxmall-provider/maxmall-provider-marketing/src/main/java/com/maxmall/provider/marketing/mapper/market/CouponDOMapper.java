package com.maxmall.provider.marketing.mapper.market;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.marketing.model.domain.market.CouponDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table SMS_COUPON.
 * 优惠卷表
 */
@Mapper
@Component
public interface CouponDOMapper extends MyMapper<CouponDO> {


}
