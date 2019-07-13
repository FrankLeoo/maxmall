package com.maxmall.provider.marketing.service.impl;

import com.maxmall.common.core.support.BaseService;
import com.maxmall.provider.marketing.mapper.market.PromotionDOMapper;
import com.maxmall.provider.marketing.mapper.market.PromotionRelationDOMapper;
import com.maxmall.provider.marketing.model.domain.market.PromotionDO;
import com.maxmall.provider.marketing.model.domain.market.PromotionRelationDO;
import com.maxmall.provider.marketing.service.PromotionRelationService;
import com.maxmall.provider.marketing.service.PromotionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ivoter
 * @ClassName OrderOperateHistoryServiceImpl.java
 * @date 2019/05/22 10:26:00
 * @Description 活动关联service
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class PromotionRelationServiceImpl extends BaseService<PromotionRelationDO> implements PromotionRelationService {

    @Autowired
    private PromotionRelationDOMapper promotionRelationDOMapper;

}
