package com.maxmall.provider.merchant.model.domain.account;

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * The table 商户用户表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "ssc_account")
public class AccountDO extends BaseEntity {

    /**
     * 商户Id.
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 用户头像.
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 备注.
     */
    @Column(name = "note")
    private String note;

    /**
     * 用户手机.
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 登录密码.
     */
    @Column(name = "login_pwd")
    private String loginPwd;

    /**
     * 用户名.
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 登录账户名.
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 商户编号.
     */
    @Column(name = "merchant_sn")
    private String merchantSn;

    /**
     * 最近登录ip.
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    /**
     * 是否主账户.
     */
    @Column(name = "is_master")
    private Boolean isMaster;

    /**
     * STATUS.
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 来源.
     */
    @Column(name = "user_source")
    private Integer userSource;

    /**
     * 最近登录时间.
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

}
