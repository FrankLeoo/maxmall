package com.maxmall.provider.merchant.service.impl;

import com.google.common.collect.Lists;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.core.support.BaseService;
import com.maxmall.common.util.PublicUtil;
import com.maxmall.common.util.converter.BeanConverter;
import com.maxmall.provider.merchant.exception.UacBizException;
import com.maxmall.provider.merchant.mapper.account.MenuDOMapper;
import com.maxmall.provider.merchant.mapper.account.RoleDOMapper;
import com.maxmall.provider.merchant.model.constant.MenuConstant;
import com.maxmall.provider.merchant.model.domain.account.MenuDO;
import com.maxmall.provider.merchant.model.domain.account.RoleDO;
import com.maxmall.provider.merchant.model.vo.MenuVo;
import com.maxmall.provider.merchant.service.MenuService;
import com.maxmall.provider.merchant.service.RoleService;
import com.maxmall.provider.merchant.utils.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ivoter
 * @ClassName MenuServiceImpl.java
 * @date 2019/05/22 10:26:00
 * @Description 菜单实现类
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl extends BaseService<MenuDO> implements MenuService {

    @Autowired
    private MenuDOMapper menuDOMapper;
    @Autowired
    private RoleService roleService;

    /**
     * 描述：查询用户的所有实现menu
     *
     * @author ivoter
     * @date 2019/5/22 10:32 AM
     * @param: userId
     * @param: merchantId
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<MenuDO> selectMenuByRoleIds(List<Long> roleIds) {
        return menuDOMapper.selectMenuByRoleIds(roleIds);
    }

    /**
     * 描述：获取tree 菜单
     *
     * @author ivoter
     * @date 2019/5/22 6:30 PM
     * @param: roleIds
     */
    @Override
    public List<MenuVo> treeMenuByRoleIds(List<Long> roleIds) {
        List<MenuDO> menuDOList = this.selectMenuByRoleIds(roleIds);

        List<MenuVo> list = getMenuVo(menuDOList);

        List<MenuVo> menuVoList = TreeUtil.getChildMenuVos(list, 0L);
        //查询数据库
        if (PublicUtil.isNotEmpty(menuVoList) && MenuConstant.MENU_ROOT.equals(menuVoList.get(0).getMenuCode())) {
            menuVoList = menuVoList.get(0).getSubMenu();
        }

        return menuVoList;
    }

    /**
     * 查询用户下所有菜单
     * @param userId
     * @return
     */
    @Override
    public List<MenuVo> getMenuVoList(Long userId) {
        List<RoleDO> roleList = roleService.selectRolesByUser(userId);
        List<Long> roleIds = roleList.stream().map(item -> item.getId()).distinct().collect(Collectors.toList());

        return this.treeMenuByRoleIds(roleIds);
    }

    @Override
    public MenuVo getMenuVoById(Long id) {
        MenuDO menuDO = menuDOMapper.selectByPrimaryKey(id);
        if (menuDO == null){
            throw new UacBizException(ErrorCodeEnum.UAC10013003);
        }
        return BeanConverter.convert(menuDO,MenuVo.class);
    }

    private List<MenuVo> getMenuVo(List<MenuDO> list) {
        List<MenuVo> menuVoList = Lists.newArrayList();
        for (MenuDO uacMenu : list) {
            MenuVo menuVo = new MenuVo();
            BeanUtils.copyProperties(uacMenu, menuVo);
            menuVo.setUrl(uacMenu.getUrl());
            menuVo.setMenuName(uacMenu.getMenuName());
            menuVoList.add(menuVo);
        }
        return menuVoList;
    }

}
