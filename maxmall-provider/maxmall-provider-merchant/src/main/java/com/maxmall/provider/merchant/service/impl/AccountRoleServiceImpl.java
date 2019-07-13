package com.maxmall.provider.merchant.service.impl;

import com.maxmall.common.core.support.BaseService;
import com.maxmall.common.util.PublicUtil;
import com.maxmall.provider.merchant.mapper.account.AccountRoleDOMapper;
import com.maxmall.provider.merchant.mapper.account.RoleDOMapper;
import com.maxmall.provider.merchant.model.domain.account.AccountRoleDO;
import com.maxmall.provider.merchant.model.domain.account.RoleDO;
import com.maxmall.provider.merchant.service.AccountRoleService;
import com.maxmall.provider.merchant.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ivoter
 * @ClassName MenuServiceImpl.java
 * @date 2019/05/22 10:26:00
 * @Description 角色实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AccountRoleServiceImpl extends BaseService<AccountRoleDO> implements AccountRoleService {

    @Autowired
    private AccountRoleDOMapper accountRoleDOMapper;


    @Override
    public int unbindRole(Long userId) {
        // 先取消对该角色的用户绑定
        Example example = new Example(AccountRoleDO.class);
        example.createCriteria().andEqualTo("userId",userId);
        return accountRoleDOMapper.deleteByExample(example);
    }

    @Override
    public int bindRoleForUser(Long userId, List<Long> roleIdList) {
        if (PublicUtil.isEmpty(roleIdList)) {
            // 取消该角色的所有用户的绑定
            logger.info("绑定角色成功");
            return 0;
        }

        List<AccountRoleDO> roleDOList = roleIdList.stream().map(item -> {
            AccountRoleDO entity = new AccountRoleDO();
            entity.setUserId(userId);
            entity.setRoleId(item);
            return entity;
        }).collect(Collectors.toList());

        // 绑定所选用户
        return this.batchSave(roleDOList);
    }
}
