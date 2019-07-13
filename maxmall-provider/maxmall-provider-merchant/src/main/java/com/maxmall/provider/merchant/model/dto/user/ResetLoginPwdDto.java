package com.maxmall.provider.merchant.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * The class Reset login pwd dto.
 *
 * @author maxmall.net@gmail.com
 */
@Data
@ApiModel
public class ResetLoginPwdDto implements Serializable {
	private static final long serialVersionUID = -3599630825681985964L;
	/**
	 * 手机号
	 */
	@NonNull
	@Length(max = 16)
	@ApiModelProperty(value = "手机号")
	private String phone;

	/**
	 * 验证码
	 */
	@NonNull
	@Length(max = 8)
	@ApiModelProperty(value = "验证码")
	private String validCode;
	/**
	 * 新密码
	 */
	@NonNull
	@Length(max = 32)
	@ApiModelProperty(value = "新密码")
	private String newPassword;

	/**
	 * 确认密码
	 */
	@NonNull
	@Length(max = 32)
	@ApiModelProperty(value = "确认密码")
	private String confirmPwd;
}
