package com.maxmall.provider.marketing.mapper.market;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.marketing.model.domain.market.PromotionNotifyDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table SMS_PROMOTION_NOTIFY.
 * 活动开始通知表
 */
@Mapper
@Component
public interface PromotionNotifyDOMapper extends MyMapper<PromotionNotifyDO> {


}
