package com.maxmall.provider.merchant.service;

import com.maxmall.common.core.support.IService;
import com.maxmall.provider.merchant.model.domain.account.AccountRoleDO;
import com.maxmall.provider.merchant.model.domain.account.RoleDO;

import java.util.List;

/**
 * @author ivoter
 * @ClassName MenuService.java
 * @date 2019/05/22 10:25:00
 * @Description 用户角色service
 */
public interface AccountRoleService extends IService<AccountRoleDO> {

    /**
     * 解绑用户角色
     * @param userId
     */
    int unbindRole(Long userId);

    /**
     * 给用户绑定角色
     * @param userId
     * @param roleIdList
     */
    int bindRoleForUser(Long userId, List<Long> roleIdList);
}
