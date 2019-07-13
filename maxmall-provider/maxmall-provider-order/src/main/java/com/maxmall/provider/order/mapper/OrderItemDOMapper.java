package com.maxmall.provider.order.mapper;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.order.model.domain.OrderItemDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table OMS_ORDER_ITEM.
 * 订单中所包含的商品
 */
@Mapper
@Component
public interface OrderItemDOMapper extends MyMapper<OrderItemDO> {


}
