package com.maxmall.provider.order.service;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.IService;
import com.maxmall.provider.order.model.domain.OrderDO;
import com.maxmall.provider.order.model.dto.OrderDeliveryDto;
import com.maxmall.provider.order.model.dto.OrderQueryDto;
import com.maxmall.provider.order.model.dto.ReceiverInfoDto;
import com.maxmall.provider.order.model.vo.OrderVo;

import java.util.List;

/**
 * @author ivoter
 * @ClassName OrderService.java
 * @date 2019/05/21 17:18:00
 * @Description 订单service
 */
public interface OrderService extends IService<OrderDO> {

    /**
     * 分页查询订单信息
     * @param queryParam
     * @param loginAuthDto
     * @return
     */
    PageInfo<OrderVo> queryOrderListWithPage(OrderQueryDto queryParam, UserTokenDto loginAuthDto);

    /**
     * 订单批量发货
     * @param deliveryParamList
     * @param loginAuthDto
     * @return
     */
    int deliveryOrder(List<OrderDeliveryDto> deliveryParamList, UserTokenDto loginAuthDto);

    /**
     * 批量删除订单
     * @param ids
     * @param loginAuthDto
     * @return
     */
    int deleteOrder(List<Long> ids, UserTokenDto loginAuthDto);

    /**
     * 订单详情
     * @param id
     * @param loginAuthDto
     * @return
     */
    OrderVo orderDetail(Long id, UserTokenDto loginAuthDto);

    /**
     * 修改收货人地址
     * @param receiverInfoParam
     * @param loginAuthDto
     * @return
     */
    int updateReceiverInfo(ReceiverInfoDto receiverInfoParam, UserTokenDto loginAuthDto);

    /**
     * 添加订单备注
     * @param id
     * @param note
     * @param loginAuthDto
     * @return
     */
    int updateNote(Long id, String note, UserTokenDto loginAuthDto);
}
