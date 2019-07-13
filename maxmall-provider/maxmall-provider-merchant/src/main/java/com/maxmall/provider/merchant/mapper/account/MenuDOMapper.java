package com.maxmall.provider.merchant.mapper.account;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.merchant.model.domain.account.MenuDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The Table SSC_MENU.
 * 商户系统菜单
 */
@Mapper
@Component
public interface MenuDOMapper extends MyMapper<MenuDO> {

    /**
     * 描述：根据角色ids查询菜单列表
     *
     * @author ivoter
     * @date 2019/5/22 11:31 AM
     * @param: roleIds
     */
    List<MenuDO> selectMenuByRoleIds(@Param("roleIds") List<Long> roleIds);
}
