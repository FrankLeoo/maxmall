package com.maxmall.provider.order.web.portal;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.BaseController;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.order.model.dto.OrderReturnReasonDto;
import com.maxmall.provider.order.model.dto.ReturnReasonQueryDto;
import com.maxmall.provider.order.model.dto.ReturnReasonStatusDto;
import com.maxmall.provider.order.model.vo.OrderReturnReasonVo;
import com.maxmall.provider.order.service.OrderReturnReasonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 退货原因管理Controller
 * Created by maxmall on 2018/4/26.
 */
@RestController
@RequestMapping(value = GlobalConstant.PORTAL_PATH + "/returnReason")
@Api(value = "WEB - OrderReturnReasonController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OrderReturnReasonController extends BaseController {

    @Autowired
    private OrderReturnReasonService orderReturnReasonService;

    @ApiOperation("添加退货原因")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Wrapper<Integer> create(@Validated @RequestBody OrderReturnReasonDto returnReason) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int count = orderReturnReasonService.saveReturnReason(returnReason,loginAuthDto);
        if (count > 0) {
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }

    @ApiOperation("修改退货原因")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public Wrapper<Integer> update(@PathVariable Long id,@Validated @RequestBody OrderReturnReasonDto returnReason) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int count = orderReturnReasonService.updateReturnReason(id, returnReason,loginAuthDto);
        if (count > 0) {
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }

    @ApiOperation("批量删除退货原因")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public Wrapper delete(@PathVariable Long id) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int count = orderReturnReasonService.deleteByIds(Lists.newArrayList(id),loginAuthDto);
        if (count > 0) {
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }

    @ApiOperation("分页查询全部退货原因")
    @RequestMapping(value = "/searchByPage", method = RequestMethod.POST)
    public Wrapper<PageInfo<OrderReturnReasonVo>> searchByPage(@Validated @RequestBody ReturnReasonQueryDto queryParam) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        PageInfo<OrderReturnReasonVo> reasonList = orderReturnReasonService.queryReasontListWithPage(queryParam, loginAuthDto);
        return WrapMapper.ok(reasonList);
    }

    @ApiOperation("获取单个退货原因详情信息")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public Wrapper<OrderReturnReasonVo> detailReason(@PathVariable Long id) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        OrderReturnReasonVo reason = orderReturnReasonService.reasonDetail(id,loginAuthDto);
        return WrapMapper.ok(reason);
    }

    @ApiOperation("修改退货原因启用状态")
    @RequestMapping(value = "/update/status", method = RequestMethod.POST)
    public Wrapper updateStatus(@Validated @RequestBody ReturnReasonStatusDto statusDto) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int count = orderReturnReasonService.updateStatus(Lists.newArrayList(statusDto.getId()), statusDto.getStatus(),loginAuthDto);
        if (count > 0) {
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }
}
