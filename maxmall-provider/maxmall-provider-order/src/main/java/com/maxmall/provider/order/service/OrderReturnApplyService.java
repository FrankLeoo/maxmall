package com.maxmall.provider.order.service;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.IService;
import com.maxmall.provider.order.model.domain.OrderReturnApplyDO;
import com.maxmall.provider.order.model.dto.ReceiveConfirmDto;
import com.maxmall.provider.order.model.dto.ReturnApplyQueryDto;
import com.maxmall.provider.order.model.dto.ApplyStatusDto;
import com.maxmall.provider.order.model.vo.OrderReturnApplyVo;

import java.util.List;

/**
 * @author ivoter
 * @ClassName OrderReturnApplyService.java
 * @date 2019/05/21 17:18:00
 * @Description 订单service
 */
public interface OrderReturnApplyService extends IService<OrderReturnApplyDO> {

    /**
     * 分页查询申请列表
     * @param queryParam
     * @param loginAuthDto
     * @return
     */
    PageInfo<OrderReturnApplyVo> queryApplyListWithPage(ReturnApplyQueryDto queryParam, UserTokenDto loginAuthDto);

    /**
     * 根据id删除申请
     * @param ids
     * @param loginAuthDto
     * @return
     */
    int deleteApply(List<Long> ids, UserTokenDto loginAuthDto);

    /**
     * 获取申请单详情
     * @param id
     * @param loginAuthDto
     * @return
     */
    OrderReturnApplyVo getDetailApply(Long id, UserTokenDto loginAuthDto);

    /**
     * 更新申请单状态
     * @param statusParam
     * @param loginAuthDto
     * @return
     */
    int applyOrder(ApplyStatusDto statusParam, UserTokenDto loginAuthDto);

    /**
     * 确认收货接口
     * @param statusParam
     * @param loginAuthDto
     * @return
     */
    int receiveConfirm(ReceiveConfirmDto statusParam, UserTokenDto loginAuthDto);
}
