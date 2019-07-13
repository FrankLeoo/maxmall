package com.maxmall.provider.order.mapper;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.order.model.domain.OrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table OMS_ORDER.
 * 订单表
 */
@Mapper
@Component
public interface OrderDOMapper extends MyMapper<OrderDO> {


}
