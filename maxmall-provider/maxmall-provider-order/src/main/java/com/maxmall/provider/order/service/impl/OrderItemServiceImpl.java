package com.maxmall.provider.order.service.impl;

import com.maxmall.common.core.support.BaseService;
import com.maxmall.provider.order.mapper.OrderItemDOMapper;
import com.maxmall.provider.order.model.domain.OrderItemDO;
import com.maxmall.provider.order.service.OrderItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ivoter
 * @ClassName OrderItemServiceImpl.java
 * @date 2019/05/22 10:26:00
 * @Description 订单管理service
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class OrderItemServiceImpl extends BaseService<OrderItemDO> implements OrderItemService {

    @Autowired
    private OrderItemDOMapper orderItemDOMapper;

}
