package com.maxmall.provider.merchant.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * The class Modify user status dto.
 *
 * @author maxmall.net@gmail.com
 */
@Data
@ApiModel(value = "用户新增参数")
public class UserModifyDto implements Serializable {

    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 用户名.
     */
    @NotEmpty
    @Length(max = 32)
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 登录账户名.
     */
    @NotEmpty
    @Length(max = 32)
    @ApiModelProperty(value = "登录账户名")
    private String loginName;

    /**
     * 用户手机.
     */
    @NotEmpty
    @Length(max = 16)
    @ApiModelProperty(value = "用户手机")
    private String phone;

    /**
     * 用户头像.
     */
    @Length(max = 255)
    @ApiModelProperty(value = "用户头像")
    private String icon;

    /**
     * 登录密码.
     */
    @NotEmpty
    @Length(max = 32)
    @ApiModelProperty(value = "登录密码.")
    private String loginPwd;

    @ApiModelProperty(value = "需要绑定的角色ID集合")
    private List<Long> roleIdList;

}
