package com.maxmall.provider.order.mapper;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.order.model.domain.OrderReturnApplyDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table OMS_ORDER_RETURN_APPLY.
 * 订单退货申请
 */
@Mapper
@Component
public interface OrderReturnApplyDOMapper extends MyMapper<OrderReturnApplyDO> {


}
