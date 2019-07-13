package com.maxmall.provider.marketing.mapper.member;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.marketing.model.domain.member.MemberGrowthHistoryDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table MSC_MEMBER_GROWTH_HISTORY.
 * 会员成长值记录表
 */
@Mapper
@Component
public interface MemberGrowthHistoryDOMapper extends MyMapper<MemberGrowthHistoryDO> {


}
