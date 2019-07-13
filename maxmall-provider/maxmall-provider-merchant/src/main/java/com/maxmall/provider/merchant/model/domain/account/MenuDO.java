package com.maxmall.provider.merchant.model.domain.account;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * The table 商户系统菜单
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "ssc_menu")
public class MenuDO extends BaseEntity {

    /**
     * 父ID.
     */
    @Column(name = "pid")
    private Long pid;

    /**
     * 资源url.
     */
    @Column(name = "url")
    private String url;

    /**
     * 图标.
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 菜单编码.
     */
    @Column(name = "menu_code")
    private String menuCode;

    /**
     * 权限.
     */
    @Column(name = "permission")
    private String permission;

    /**
     * 权限名称.
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 权限描述.
     */
    @Column(name = "description")
    private String description;

    /**
     * 排序.
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 类型.
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 状态.
     */
    @Column(name = "status")
    private Integer status;

}
