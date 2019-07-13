package com.maxmall.provider.merchant.model.domain.mapper;

import com.maxmall.common.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table MCS_DISTRIBUTOR_MONEY_HISTORY.
 * 分销商操作记录表
 */
@Mapper
@Component
public interface McsDistributorMoneyHistoryDOMapper extends MyMapper<McsDistributorMoneyHistoryDO> {


}
