package com.maxmall.provider.merchant.mapper.merchant;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.merchant.model.domain.merchant.MerchantAuditDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table MCS_MERCHANT_AUDIT.
 * MCS_MERCHANT_AUDIT
 */
@Mapper
@Component
public interface MerchantAuditDOMapper extends MyMapper<MerchantAuditDO> {


}
