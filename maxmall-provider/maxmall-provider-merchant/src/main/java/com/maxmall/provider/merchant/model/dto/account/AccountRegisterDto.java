package com.maxmall.provider.merchant.model.dto.account;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户注册参数
 */
@Data
@ApiModel(value = "用户注册参数")
public class AccountRegisterDto implements Serializable {

    private static final long serialVersionUID = 8391763073747981998L;

    @ApiModelProperty(value = "用户头像")
    @Length(max=255)
    private String icon;

    @ApiModelProperty(value = "用户名")
    @Length(max=32)
    private String userName;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "登录名")
    @NotNull
    @Length(max=32)
    private String loginName;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String loginPwd;

    /**
     * 确认密码
     */
    @ApiModelProperty(value = "确认密码")
    private String confirmPwd;

    /**
     * 手机验证码
     */
    @NotNull
    @Length(max=8)
    @ApiModelProperty(value = "手机验证码")
    private String validCode;

    /**
     * 商户注册信息
     */
    @NotNull
    @ApiModelProperty(value = "商户信息")
    private MerchantRegisterDto merchant;
}
