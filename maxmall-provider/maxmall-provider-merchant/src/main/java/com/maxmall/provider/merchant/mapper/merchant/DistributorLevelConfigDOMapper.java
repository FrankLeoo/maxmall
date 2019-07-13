package com.maxmall.provider.merchant.mapper.merchant;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.merchant.model.domain.merchant.DistributorLevelConfigDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table MCS_DISTRIBUTOR_LEVEL_CONFIG.
 * 分销商等级规则表
 */
@Mapper
@Component
public interface DistributorLevelConfigDOMapper extends MyMapper<DistributorLevelConfigDO> {


}
