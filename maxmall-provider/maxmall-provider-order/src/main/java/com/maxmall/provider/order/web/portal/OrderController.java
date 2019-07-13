package com.maxmall.provider.order.web.portal;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.BaseController;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.order.model.dto.*;
import com.maxmall.provider.order.model.vo.OrderVo;
import com.maxmall.provider.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 订单管理Controller
 * Created by maxmall on 2018/4/26.
 */
@RestController
@RequestMapping(value = GlobalConstant.PORTAL_PATH + "/order")
@Api(value = "WEB - OrderController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("查询订单")
    @RequestMapping(value = "/searchByPage", method = RequestMethod.POST)
    public Wrapper<PageInfo<OrderVo>> searchByPage(@Validated @RequestBody OrderQueryDto queryParam) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        PageInfo<OrderVo> orderList = orderService.queryOrderListWithPage(queryParam, loginAuthDto);
        return WrapMapper.ok(orderList);
    }

    @ApiOperation("批量发货")
    @RequestMapping(value = "/update/delivery", method = RequestMethod.POST)
    public Wrapper<Integer> delivery(@Validated @RequestBody List<OrderDeliveryDto> deliveryParamList) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int count = orderService.deliveryOrder(deliveryParamList,loginAuthDto);
        if (count > 0) {
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }

    @ApiOperation("批量删除订单")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Wrapper<Integer> delete(@RequestParam("ids") List<Long> ids) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int count = orderService.deleteOrder(ids,loginAuthDto);
        if (count > 0) {
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }

    @ApiOperation("获取订单详情:订单信息、商品信息、操作记录")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public Wrapper<OrderVo> detail(@PathVariable Long id) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        OrderVo orderDetailResult = orderService.orderDetail(id,loginAuthDto);
        return WrapMapper.ok(orderDetailResult);
    }

    @ApiOperation("修改收货人信息")
    @RequestMapping(value = "/update/receiverInfo", method = RequestMethod.POST)
    public Wrapper<Integer> updateReceiverInfo(@Validated @RequestBody ReceiverInfoDto receiverInfoParam) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int count = orderService.updateReceiverInfo(receiverInfoParam,loginAuthDto);
        if (count > 0) {
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }

    @ApiOperation("备注订单")
    @RequestMapping(value = "/update/note", method = RequestMethod.POST)
    public Wrapper<Integer> updateNote(@RequestParam("id") Long id,
                                   @RequestParam("note") String note) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int count = orderService.updateNote(id, note, loginAuthDto);
        if (count > 0) {
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }
}
