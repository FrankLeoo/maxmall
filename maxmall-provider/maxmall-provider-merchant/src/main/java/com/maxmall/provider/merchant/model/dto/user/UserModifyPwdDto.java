package com.maxmall.provider.merchant.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * The class User modify pwd dto.
 *
 * @author maxmall.net@gmail.com
 */
@Data
@ApiModel(value = "用户修改密码Dto")
public class UserModifyPwdDto implements Serializable {

	private static final long serialVersionUID = -3933378415083541145L;
	/**
	 * 登录名
	 */
	@NotNull
	@ApiModelProperty(value = "用户ID")
	private Long userId;

	/**
	 * 新密码
	 */
	@NotEmpty
	@ApiModelProperty(value = "新密码")
	private String newPassword;

	/**
	 * 确认密码
	 */
	@NotEmpty
	@ApiModelProperty(value = "确认密码")
	private String confirmPwd;

}
