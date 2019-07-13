package com.maxmall.provider.marketing.web.portal;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.BaseController;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.marketing.model.dto.MemberAddressQueryDto;
import com.maxmall.provider.marketing.model.dto.MemberGrowthQueryDto;
import com.maxmall.provider.marketing.model.dto.MemberIntegrationQueryDto;
import com.maxmall.provider.marketing.model.dto.MemberQueryDto;
import com.maxmall.provider.marketing.model.vo.MemberAddressVo;
import com.maxmall.provider.marketing.model.vo.MemberGrowthVo;
import com.maxmall.provider.marketing.model.vo.MemberIntegrationVo;
import com.maxmall.provider.marketing.model.vo.MemberVo;
import com.maxmall.provider.marketing.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 会员管理Controller
 * Created by maxmall on 2018/4/26.
 */
@RestController
@RequestMapping(value = GlobalConstant.PORTAL_PATH + "/member")
@Api(value = "WEB - MemberController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MemberController extends BaseController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "删除禁用会员", httpMethod = "POST")
    @PostMapping(value = "/delete/{id}")
    public Wrapper<Integer> delete(@PathVariable Long id) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int count = memberService.deleteMember(id,loginAuthDto);
        if(count>0){
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }

    @ApiOperation(value = "分页获取会员列表", httpMethod = "POST")
    @PostMapping(value = "/searchByPage")
    public Wrapper<PageInfo<MemberVo>> searchByPage(@Validated @RequestBody MemberQueryDto queryParam){
        UserTokenDto loginAuthDto = getLoginAuthDto();

        PageInfo<MemberVo> orderList = memberService.queryMemberListWithPage(queryParam, loginAuthDto);
        return WrapMapper.ok(orderList);
    }

    @ApiOperation(value = "获取单个会员的详细信息", httpMethod = "GET")
    @GetMapping(value = "/detail/{id}")
    public Wrapper<MemberVo> couponDetail(@PathVariable Long id) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        MemberVo couponParam = memberService.memberDetail(id,loginAuthDto);
        return WrapMapper.ok(couponParam);
    }

    @ApiOperation(value = "分页获取会员成长值列表", httpMethod = "POST")
    @PostMapping(value = "/growth/searchByPage")
    public Wrapper<PageInfo<MemberGrowthVo>> searchGrowthByPage(@Validated @RequestBody MemberGrowthQueryDto queryParam){
        UserTokenDto loginAuthDto = getLoginAuthDto();

        PageInfo<MemberGrowthVo> orderList = memberService.queryGrowthListWithPage(queryParam, loginAuthDto);
        return WrapMapper.ok(orderList);
    }

    @ApiOperation(value = "分页获取会员积分列表", httpMethod = "POST")
    @PostMapping(value = "/integration/searchByPage")
    public Wrapper<PageInfo<MemberIntegrationVo>> searchIntegrationByPage(@Validated @RequestBody MemberIntegrationQueryDto queryParam){
        UserTokenDto loginAuthDto = getLoginAuthDto();

        PageInfo<MemberIntegrationVo> orderList = memberService.queryIntegrationListWithPage(queryParam, loginAuthDto);
        return WrapMapper.ok(orderList);
    }

    @ApiOperation(value = "分页获取会员地址列表", httpMethod = "POST")
    @PostMapping(value = "/address/searchByPage")
    public Wrapper<PageInfo<MemberAddressVo>> searchAddressByPage(@Validated @RequestBody MemberAddressQueryDto queryParam){
        UserTokenDto loginAuthDto = getLoginAuthDto();

        PageInfo<MemberAddressVo> orderList = memberService.queryAddressListWithPage(queryParam, loginAuthDto);
        return WrapMapper.ok(orderList);
    }
}
