package com.maxmall.provider.merchant.mapper.merchant;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.merchant.model.domain.merchant.DistributorAuditDO;
import com.maxmall.provider.merchant.model.domain.merchant.DistributorDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table MCS_DISTRIBUTOR_AUDIT.
 * 分销商表 审核表
 */
@Mapper
@Component
public interface DistributorAuditDOMapper extends MyMapper<DistributorAuditDO> {


}
