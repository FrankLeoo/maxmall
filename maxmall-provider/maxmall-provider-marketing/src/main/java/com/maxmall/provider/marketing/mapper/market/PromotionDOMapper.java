package com.maxmall.provider.marketing.mapper.market;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.marketing.model.domain.market.PromotionDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table SMS_PROMOTION.
 * 活动信息表
 */
@Mapper
@Component
public interface PromotionDOMapper extends MyMapper<PromotionDO> {


}
