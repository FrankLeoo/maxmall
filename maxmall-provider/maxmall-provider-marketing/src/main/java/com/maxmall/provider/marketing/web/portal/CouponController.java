package com.maxmall.provider.marketing.web.portal;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.core.support.BaseController;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.marketing.model.dto.CouponModifyDto;
import com.maxmall.provider.marketing.model.dto.CouponQueryDto;
import com.maxmall.provider.marketing.model.enums.CouponUseTypeEnum;
import com.maxmall.provider.marketing.model.vo.CouponVo;
import com.maxmall.provider.marketing.service.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 优惠券管理Controller
 * Created by maxmall on 2018/4/26.
 */
@RestController
@RequestMapping(value = GlobalConstant.PORTAL_PATH + "/coupon")
@Api(value = "WEB - CouponController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CouponController extends BaseController {

    @Autowired
    private CouponService couponService;

    @ApiOperation(value = "添加或更新优惠券", httpMethod = "POST")
    @PostMapping(value = "/modify")
    public Wrapper<Integer> modifyCoupon(@Validated @RequestBody CouponModifyDto couponParam) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        CouponUseTypeEnum type = CouponUseTypeEnum.getByType(couponParam.getUseType());
        if (type != CouponUseTypeEnum.ALL && CollectionUtils.isEmpty(couponParam.getRelationList())){
            return WrapMapper.error(ErrorCodeEnum.TPC100600002);
        }

        int count = couponService.saveOrModifyCoupon(couponParam,loginAuthDto);
        if(count>0){
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }

    @ApiOperation(value = "删除优惠券", httpMethod = "POST")
    @PostMapping(value = "/delete/{id}")
    public Wrapper<Integer> deleteCoupon(@PathVariable Long id) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int count = couponService.deleteCoupon(id,loginAuthDto);
        if(count>0){
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }

    @ApiOperation(value = "分页获取优惠券列表", httpMethod = "POST")
    @PostMapping(value = "/searchByPage")
    public Wrapper<PageInfo<CouponVo>> searchByPage(@Validated @RequestBody CouponQueryDto queryParam){
        UserTokenDto loginAuthDto = getLoginAuthDto();

        PageInfo<CouponVo> orderList = couponService.queryCouponListWithPage(queryParam, loginAuthDto);
        return WrapMapper.ok(orderList);
    }

    /**
     * 优惠券列表
     * @return
     */
    @PostMapping(value = "/listAll")
    @ApiOperation(httpMethod = "POST", value = "全部优惠券列表")
    public Wrapper<List<CouponVo>> queryAllList(@Validated @RequestBody CouponQueryDto queryParam) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        List<CouponVo> result = couponService.couponListAll(loginAuthDto,queryParam);
        return WrapMapper.ok(result);
    }

    @ApiOperation(value = "获取单个优惠券的详细信息", httpMethod = "GET")
    @GetMapping(value = "/detail/{id}")
    public Wrapper<CouponVo> couponDetail(@PathVariable Long id) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        CouponVo couponParam = couponService.couponDetail(id,loginAuthDto);
        return WrapMapper.ok(couponParam);
    }


}
