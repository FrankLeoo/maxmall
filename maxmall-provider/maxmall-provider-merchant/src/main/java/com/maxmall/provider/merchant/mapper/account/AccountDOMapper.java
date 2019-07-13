package com.maxmall.provider.merchant.mapper.account;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.merchant.model.domain.account.AccountDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table SSC_ACCOUNT.
 * 商户用户表
 */
@Mapper
@Component
public interface AccountDOMapper extends MyMapper<AccountDO> {

    /**
     * Find by login name uac user.
     *
     * @param loginName the login name
     *
     * @return the uac user
     */
    AccountDO findByLoginName(String loginName);

    /**
     * Find by phone uac user.
     *
     * @param phone the login name
     *
     * @return the uac user
     */
    AccountDO findByPhone(String phone);
}
