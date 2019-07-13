package com.maxmall.provider.merchant.service;

import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.IService;
import com.maxmall.provider.merchant.model.domain.account.MenuDO;
import com.maxmall.provider.merchant.model.vo.MenuVo;

import java.util.List;

/**
 * @author ivoter
 * @ClassName MenuService.java
 * @date 2019/05/22 10:25:00
 * @Description 菜单service
 */
public interface MenuService extends IService<MenuDO> {

    /**
     * 描述：根据角色id列表查询menu菜单
     *
     * @author ivoter
     * @date 2019/5/22 11:30 AM
     * @param: roleIds
     */
    List<MenuDO> selectMenuByRoleIds(List<Long> roleIds);

    /**
     * 描述：菜单tree
     *
     * @author ivoter
     * @date 2019/5/22 6:21 PM
     * @param: roleIds
     */
    List<MenuVo> treeMenuByRoleIds(List<Long> roleIds);

    /**
     * 查询用户菜单
     * @param userId
     * @return
     */
    List<MenuVo> getMenuVoList(Long userId);

    /**
     * 查询菜单详情
     * @param id
     * @return
     */
    MenuVo getMenuVoById(Long id);
}
