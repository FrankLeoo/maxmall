package com.maxmall.provider.order.mapper;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.order.model.domain.OrderOperateHistoryDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table OMS_ORDER_OPERATE_HISTORY.
 * 订单操作历史记录
 */
@Mapper
@Component
public interface OrderOperateHistoryDOMapper extends MyMapper<OrderOperateHistoryDO> {


}
