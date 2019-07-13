package com.maxmall.provider.marketing.mapper.member;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.marketing.model.domain.member.MemberAddressDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table MSC_MEMBER_ADDRESS.
 * 会员收货地址表
 */
@Mapper
@Component
public interface MemberAddressDOMapper extends MyMapper<MemberAddressDO> {


}
