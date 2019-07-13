package com.maxmall.provider.merchant.mapper.merchant;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.merchant.model.domain.merchant.DistributorDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table MCS_DISTRIBUTOR.
 * 分销商表
 */
@Mapper
@Component
public interface DistributorDOMapper extends MyMapper<DistributorDO> {


}
