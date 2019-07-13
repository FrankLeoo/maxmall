package com.maxmall.provider.marketing.web.portal;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.core.support.BaseController;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.marketing.model.dto.CouponModifyDto;
import com.maxmall.provider.marketing.model.dto.MemberCouponGiveDto;
import com.maxmall.provider.marketing.model.dto.MemberCouponQueryDto;
import com.maxmall.provider.marketing.model.enums.CouponUseTypeEnum;
import com.maxmall.provider.marketing.model.vo.MemberCouponVo;
import com.maxmall.provider.marketing.service.MemberCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员优惠券controller
 */
@RestController
@RequestMapping(value = GlobalConstant.PORTAL_PATH + "/member/coupon")
@Api(value = "WEB - MemberCouponController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MemberCouponController extends BaseController {

    @Autowired
    private MemberCouponService memberCouponService;

    @ApiOperation(value = "分页获取用户优惠券列表", httpMethod = "POST")
    @PostMapping(value = "/searchByPage")
    public Wrapper<PageInfo<MemberCouponVo>> searchByPage(@Validated @RequestBody MemberCouponQueryDto queryParam){
        UserTokenDto loginAuthDto = getLoginAuthDto();

        PageInfo<MemberCouponVo> orderList = memberCouponService.queryCouponListWithPage(queryParam, loginAuthDto);
        return WrapMapper.ok(orderList);
    }

    /**
     * 给会员发送优惠券
     */
    @ApiOperation(value = "赠送优惠券给会员", httpMethod = "POST")
    @PostMapping(value = "/give")
    public Wrapper<Integer> giveCoupon(@Validated @RequestBody MemberCouponGiveDto couponParam) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int count = memberCouponService.giveCoupon(couponParam,loginAuthDto);
        if(count>0){
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }

}
