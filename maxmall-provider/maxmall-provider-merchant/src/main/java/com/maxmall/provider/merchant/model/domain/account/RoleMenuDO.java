package com.maxmall.provider.merchant.model.domain.account;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * The table SSC_ROLE_MENU
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "ssc_role_menu")
public class RoleMenuDO extends BaseEntity {

    /**
     * 角色id.
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 菜单id.
     */
    @Column(name = "menu_id")
    private Long menuId;

}
