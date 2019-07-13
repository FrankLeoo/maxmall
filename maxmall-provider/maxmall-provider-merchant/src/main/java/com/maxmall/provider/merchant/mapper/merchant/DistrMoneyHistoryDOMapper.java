package com.maxmall.provider.merchant.mapper.merchant;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.merchant.model.domain.merchant.DistrMoneyHistoryDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table MCS_DISTRIBUTOR_MONEY_HISTORY.
 * 分销商操作记录表
 */
@Mapper
@Component
public interface DistrMoneyHistoryDOMapper extends MyMapper<DistrMoneyHistoryDO> {


}
