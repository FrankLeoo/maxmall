package com.maxmall.provider.merchant.model.domain.account;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * The table 商户角色
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "ssc_role")
public class RoleDO extends BaseEntity {

    /**
     * 角色编号.
     */
    @Column(name = "code")
    private String code;

    /**
     * 角色名称.
     */
    @Column(name = "name")
    private String name;

    /**
     * 角色描述.
     */
    @Column(name = "description")
    private String description;

    /**
     * 排序.
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 状态.
     */
    @Column(name = "status")
    private Integer status;

}
