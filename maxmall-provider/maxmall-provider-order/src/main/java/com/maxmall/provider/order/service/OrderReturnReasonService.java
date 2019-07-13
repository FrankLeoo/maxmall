package com.maxmall.provider.order.service;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.IService;
import com.maxmall.provider.order.model.domain.OrderReturnReasonDO;
import com.maxmall.provider.order.model.dto.OrderReturnReasonDto;
import com.maxmall.provider.order.model.dto.ReturnReasonQueryDto;
import com.maxmall.provider.order.model.vo.OrderReturnReasonVo;

import java.util.List;

/**
 * @author ivoter
 * @ClassName OrderReturnReasonService.java
 * @date 2019/05/21 17:18:00
 * @Description 订单service
 */
public interface OrderReturnReasonService extends IService<OrderReturnReasonDO> {

    /**
     * 保存 退款原因
     * @param returnReason
     * @param loginAuthDto
     * @return
     */
    int saveReturnReason(OrderReturnReasonDto returnReason, UserTokenDto loginAuthDto);

    /**
     * 更新退款原因
     * @param id
     * @param returnReason
     * @param loginAuthDto
     * @return
     */
    int updateReturnReason(Long id, OrderReturnReasonDto returnReason, UserTokenDto loginAuthDto);

    /**
     * 根据id 删除退款原因
     * @param ids
     * @param loginAuthDto
     * @return
     */
    int deleteByIds(List<Long> ids, UserTokenDto loginAuthDto);

    /**
     * 分页查询退款原因
     * @param queryParam
     * @param loginAuthDto
     * @return
     */
    PageInfo<OrderReturnReasonVo> queryReasontListWithPage(ReturnReasonQueryDto queryParam, UserTokenDto loginAuthDto);

    /**
     * 查询退款原因 详情
     * @param id
     * @param loginAuthDto
     * @return
     */
    OrderReturnReasonVo reasonDetail(Long id,UserTokenDto loginAuthDto);

    /**
     * 根据id列表更新状态
     * @param ids
     * @param status
     * @param loginAuthDto
     * @return
     */
    int updateStatus(List<Long> ids, Integer status, UserTokenDto loginAuthDto);
}