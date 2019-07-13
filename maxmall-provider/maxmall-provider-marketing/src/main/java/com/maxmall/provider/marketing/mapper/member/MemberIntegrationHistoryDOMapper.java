package com.maxmall.provider.marketing.mapper.member;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.marketing.model.domain.member.MemberIntegrationHistoryDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table MSC_MEMBER_INTEGRATION_HISTORY.
 * 会员积分记录表
 */
@Mapper
@Component
public interface MemberIntegrationHistoryDOMapper extends MyMapper<MemberIntegrationHistoryDO> {


}
