package com.maxmall.provider.order.web.portal;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.BaseController;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.order.model.dto.*;
import com.maxmall.provider.order.model.vo.OrderReturnApplyVo;
import com.maxmall.provider.order.service.OrderReturnApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 订单退货申请管理Controller
 * Created by maxmall on 2018/4/26.
 */
@RestController
@RequestMapping(value =  GlobalConstant.PORTAL_PATH + "/returnApply")
@Api(value = "WEB - OrderReturnApplyController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OrderReturnApplyController extends BaseController {

    @Autowired
    private OrderReturnApplyService returnApplyService;

    @ApiOperation("分页查询退货申请")
    @RequestMapping(value = "/searchByPage", method = RequestMethod.POST)
    public Wrapper<PageInfo<OrderReturnApplyVo>> searchByPage(@Validated @RequestBody ReturnApplyQueryDto queryParam) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        PageInfo<OrderReturnApplyVo> reasonList = returnApplyService.queryApplyListWithPage(queryParam, loginAuthDto);
        return WrapMapper.ok(reasonList);
    }

    @ApiOperation("批量删除申请")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Wrapper<Integer> delete(@RequestParam("ids") List<Long> ids) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int count = returnApplyService.deleteApply(ids,loginAuthDto);
        if (count > 0) {
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }

    @ApiOperation("获取退货申请详情")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public Wrapper<OrderReturnApplyVo> getApply(@PathVariable Long id) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        OrderReturnApplyVo result = returnApplyService.getDetailApply(id,loginAuthDto);
        return WrapMapper.ok(result);
    }

    @ApiOperation("审核退款单")
    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public Wrapper<Integer> applyOrder(@Validated @RequestBody ApplyStatusDto statusParam) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int count = returnApplyService.applyOrder(statusParam,loginAuthDto);
        if (count > 0) {
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }

    @ApiOperation("确认收货")
    @RequestMapping(value = "/receiveConfirm", method = RequestMethod.POST)
    public Wrapper<Integer> receiveConfirm(@Validated @RequestBody ReceiveConfirmDto statusParam) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int count = returnApplyService.receiveConfirm(statusParam,loginAuthDto);
        if (count > 0) {
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }

}
