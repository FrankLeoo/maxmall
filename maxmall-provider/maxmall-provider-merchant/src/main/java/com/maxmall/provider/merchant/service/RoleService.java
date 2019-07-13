package com.maxmall.provider.merchant.service;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.IService;
import com.maxmall.provider.merchant.model.domain.account.RoleDO;
import com.maxmall.provider.merchant.model.dto.role.RoleBindUserDto;
import com.maxmall.provider.merchant.model.dto.role.RoleBindUserReqDto;
import com.maxmall.provider.merchant.model.dto.user.QueryRoleDto;
import com.maxmall.provider.merchant.model.vo.RoleVo;

import java.util.List;

/**
 * @author ivoter
 * @ClassName MenuService.java
 * @date 2019/05/22 10:25:00
 * @Description 角色service
 */
public interface RoleService extends IService<RoleDO> {

    /**
     * 描述：根据用户id获取角色列表
     *
     * @author ivoter
     * @date 2019/5/22 11:50 AM
     * @param: userId
     */
    List<RoleDO> selectRolesByUser(Long userId);

    /**
     * 分页查询角色
     * @param queryRoleDto
     * @return
     */
    PageInfo<RoleVo> queryRoleListWithPage(QueryRoleDto queryRoleDto);

    /**
     * 获取角色详情
     * @param id
     * @return
     */
    RoleVo queryRoleInfo(Long id);

    /**
     * 角色绑定用户
     * @param roleBindUserReqDto
     * @param loginAuthDto
     */
    void bindUser4Role(RoleBindUserReqDto roleBindUserReqDto, UserTokenDto loginAuthDto);

    /**
     * 获取用户绑定角色
     * @param roleId
     * @param loginAuthDto
     * @return
     */
    RoleBindUserDto getRoleBindUserDto(Long roleId, UserTokenDto loginAuthDto);
}
