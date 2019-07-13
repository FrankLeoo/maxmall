package com.maxmall.provider.merchant.mapper.merchant;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.merchant.model.domain.merchant.DistrCommissionConfigDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table MCS_DISTRIBUTOR_COMMISSION_CONFIG.
 * 分销商销售返点规则表
 */
@Mapper
@Component
public interface DistrCommissionConfigDOMapper extends MyMapper<DistrCommissionConfigDO> {


}
