package com.maxmall.provider.merchant.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户详情vo
 */
@Data
public class AccountInfoVo implements Serializable {

    /**
     * 是否主账户.
     */
    private Boolean isMaster;
    /**
     * 用户手机.
     */
    private String phone;

    /**
     * 用户名.
     */
    private String userName;

    /**
     * 用户头像.
     */
    private String icon;

    /**
     * 角色列表
     */
    private List<RoleVo> roles;
}
