package com.maxmall.provider.marketing.mapper.member;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.marketing.model.domain.member.MemberDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table MSC_MEMBER.
 * 会员信息表
 */
@Mapper
@Component
public interface MemberDOMapper extends MyMapper<MemberDO> {


}
