package com.maxmall.provider.merchant.model.vo;

import com.maxmall.common.base.dto.BaseVo;
import lombok.Data;

@Data
public class AccountVo extends BaseVo {

    /**
     * 商户Id.
     */
    private Long merchantId;

    /**
     * 用户头像.
     */
    private String icon;

    /**
     * 备注.
     */
    private String note;

    /**
     * 用户手机.
     */
    private String phone;

    /**
     * 用户名.
     */
    private String userName;

    /**
     * 登录账户名.
     */
    private String loginName;

    /**
     * 商户编号.
     */
    private String merchantSn;

    /**
     * 最近登录ip.
     */
    private String lastLoginIp;

    /**
     * 是否主账户.
     */
    private Boolean isMaster;

    /**
     * STATUS.
     */
    private Integer status;

    /**
     * 来源.
     */
    private Integer userSource;

    /**
     * 最近登录时间.
     */
    private Integer lastLoginTime;
}
