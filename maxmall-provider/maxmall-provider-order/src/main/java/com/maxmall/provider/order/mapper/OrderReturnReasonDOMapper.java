package com.maxmall.provider.order.mapper;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.order.model.domain.OrderReturnReasonDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table OMS_ORDER_RETURN_REASON.
 * 退货原因表
 */
@Mapper
@Component
public interface OrderReturnReasonDOMapper extends MyMapper<OrderReturnReasonDO> {


}
