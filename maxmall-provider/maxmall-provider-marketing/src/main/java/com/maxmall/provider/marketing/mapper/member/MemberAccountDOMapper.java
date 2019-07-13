package com.maxmall.provider.marketing.mapper.member;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.marketing.model.domain.member.MemberAccountDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table MSC_MEMBER_ACCOUNT.
 * 会员统计账目表
 */
@Mapper
@Component
public interface MemberAccountDOMapper extends MyMapper<MemberAccountDO> {


}
