package com.maxmall.provider.merchant.service;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.IService;
import com.maxmall.provider.merchant.model.domain.account.AccountDO;
import com.maxmall.provider.merchant.model.dto.account.AccountRegisterDto;
import com.maxmall.provider.merchant.model.dto.user.*;
import com.maxmall.provider.merchant.model.vo.AccountInfoVo;
import com.maxmall.provider.merchant.model.vo.AccountVo;
import com.maxmall.provider.merchant.model.vo.UserBindRoleVo;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * @author ivoter
 * @ClassName AccountService.java
 * @date 2019/05/21 17:18:00
 * @Description 商户用户表
 */
public interface AccountService extends IService<AccountDO> {

    /**
     * 根据登录名查询用户信息
     *
     * @param loginName the login name
     *
     * @return the uac user
     */
    AccountDO findByLoginName(String loginName);

    /**
     * 根据手机号查询用户信息.
     *
     * @param phone the mobile no
     *
     * @return the uac user
     */
    AccountDO findByPhone(String phone);

    /**
     * 描述：获取当前登录用户menus
     *
     * @author ivoter
     * @date 2019/5/21 6:04 PM
     */
    LoginRespDto findMenusByAccount(UserTokenDto loginAuthDto);

    /**
     * 描述：查询用户权限列表
     *
     * @author ivoter
     * @date 2019/5/22 10:50 AM
     */
    Collection<GrantedAuthority> loadUserAuthorities(Long accountId);

    /**
     * 验证登录名
     * @param loginName
     * @return
     */
    boolean checkLoginName(String loginName);

    /**
     * 验证手机号
     * @param phone
     * @return
     */
    boolean checkPhone(String phone);

    /**
     * 重置密码
     * @param resetLoginPwdDto
     */
    void resetLoginPwd(ResetLoginPwdDto resetLoginPwdDto);

    /**
     * 用户注册接口
     * @param user
     */
    int register(AccountRegisterDto user);

    /**
     * 查询商户下子用户列表
     *
     * @param queryUserDto
     * @param loginAuthDto
     * @return
     */
    PageInfo<AccountVo> queryUserListWithPage(QueryUserDto queryUserDto, UserTokenDto loginAuthDto);

    /**
     * 更新用户状态
     * @param modifyUserStatusDto
     * @param loginAuthDto
     * @return
     */
    int modifyUserStatusById(ModifyUserStatusDto modifyUserStatusDto, UserTokenDto loginAuthDto);

    /**
     * 根据id删除用户
     * @param userId
     * @param loginAuthDto
     * @return
     */
    int deleteUserById(Long userId, UserTokenDto loginAuthDto);

    /**
     * 根据ID查询用户信息
     * @param userId
     * @return
     */
    AccountVo queryByUserId(Long userId, UserTokenDto loginAuthDto);

    /**
     * 用户密码修改
     * @param userModifyPwdDto
     * @param loginAuthDto
     * @return
     */
    int userModifyPwd(UserModifyPwdDto userModifyPwdDto, UserTokenDto loginAuthDto);

    /**
     * 给用户绑定角色
     * @param bindUserRolesDto
     * @param loginAuthDto
     */
    int bindUserRoles(BindUserRolesDto bindUserRolesDto, UserTokenDto loginAuthDto);

    /**
     * 获取用户角色绑定数据
     * @param userId
     * @return
     */
    List<UserBindRoleVo> getUserBindRoleDto(Long userId);

    /**
     * 添加子用户
     * @param userModifyDto
     * @param loginAuthDto
     */
    int saveOrModifyUser(UserModifyDto userModifyDto, UserTokenDto loginAuthDto);

    /**
     * 获取用户登录详情
     * @param userTokenDto
     * @return
     */
    AccountInfoVo accountInfo(UserTokenDto userTokenDto);
}
