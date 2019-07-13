package com.maxmall.provider.order.service.impl;

import com.maxmall.common.core.support.BaseService;
import com.maxmall.provider.order.mapper.OrderOperateHistoryDOMapper;
import com.maxmall.provider.order.model.domain.OrderOperateHistoryDO;
import com.maxmall.provider.order.service.OrderOperateHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ivoter
 * @ClassName OrderOperateHistoryServiceImpl.java
 * @date 2019/05/22 10:26:00
 * @Description 订单操作日志管理service
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class OrderOperateHistoryServiceImpl extends BaseService<OrderOperateHistoryDO> implements OrderOperateHistoryService {

    @Autowired
    private OrderOperateHistoryDOMapper orderOperateHistoryDOMapper;

}
