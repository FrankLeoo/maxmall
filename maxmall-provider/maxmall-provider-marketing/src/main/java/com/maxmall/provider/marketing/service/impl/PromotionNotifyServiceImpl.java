package com.maxmall.provider.marketing.service.impl;

import com.maxmall.common.core.support.BaseService;
import com.maxmall.provider.marketing.mapper.market.PromotionNotifyDOMapper;
import com.maxmall.provider.marketing.model.domain.market.PromotionNotifyDO;
import com.maxmall.provider.marketing.service.PromotionNotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ivoter
 * @ClassName OrderOperateHistoryServiceImpl.java
 * @date 2019/05/22 10:26:00
 * @Description 活动通知service
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class PromotionNotifyServiceImpl extends BaseService<PromotionNotifyDO> implements PromotionNotifyService {

    @Autowired
    private PromotionNotifyDOMapper promotionNotifyDOMapper;

}
