package com.maxmall.provider.merchant.mapper.account;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.merchant.model.domain.account.AccountRoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table SSC_ACCOUNT_ROLE.
 * SSC_ACCOUNT_ROLE
 */
@Mapper
@Component
public interface AccountRoleDOMapper extends MyMapper<AccountRoleDO> {


}
