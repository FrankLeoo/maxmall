package com.maxmall.provider.merchant.mapper.merchant;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.merchant.model.domain.merchant.MemberLevelConfigDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table MCS_MERCHANT_LEVEL_CONFIG.
 * 商户会员等级规则表
 */
@Mapper
@Component
public interface MemberLevelConfigDOMapper extends MyMapper<MemberLevelConfigDO> {


}
