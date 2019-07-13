package com.maxmall.provider.marketing.service;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.IService;
import com.maxmall.provider.marketing.model.domain.market.CouponDO;
import com.maxmall.provider.marketing.model.domain.market.MemberCouponDO;
import com.maxmall.provider.marketing.model.dto.MemberCouponGiveDto;
import com.maxmall.provider.marketing.model.dto.MemberCouponQueryDto;
import com.maxmall.provider.marketing.model.vo.MemberCouponVo;

/**
 * @author ivoter
 * @ClassName OrderItemService.java
 * @date 2019/05/21 17:18:00
 * @Description 会员优惠券service
 */
public interface MemberCouponService extends IService<MemberCouponDO> {

    /**
     * 分页获取优惠券
     * @param queryParam
     * @param loginAuthDto
     * @return
     */
    PageInfo<MemberCouponVo> queryCouponListWithPage(MemberCouponQueryDto queryParam, UserTokenDto loginAuthDto);

    /**
     * 给会员赠送优惠券
     * @param couponParam
     * @param loginAuthDto
     * @return
     */
    int giveCoupon(MemberCouponGiveDto couponParam, UserTokenDto loginAuthDto);
}
