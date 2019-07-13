package com.maxmall.provider.marketing.service.impl;

import com.maxmall.common.core.support.BaseService;
import com.maxmall.provider.marketing.mapper.market.CouponDOMapper;
import com.maxmall.provider.marketing.mapper.market.CouponRelationDOMapper;
import com.maxmall.provider.marketing.model.domain.market.CouponRelationDO;
import com.maxmall.provider.marketing.service.CouponRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ivoter
 * @ClassName OrderOperateHistoryServiceImpl.java
 * @date 2019/05/22 10:26:00
 * @Description 优惠券service
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class CouponRealationServiceImpl extends BaseService<CouponRelationDO> implements CouponRelationService {

    @Autowired
    private CouponRelationDOMapper couponRelationDOMapper;

}
