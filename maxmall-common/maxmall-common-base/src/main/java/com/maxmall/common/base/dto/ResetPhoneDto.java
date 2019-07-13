package com.maxmall.common.base.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * The class Check valid dto.
 *
 * @author maxmall.net@gmail.com
 */
@Data
@ApiModel
public class ResetPhoneDto implements Serializable {
	private static final long serialVersionUID = 5178470476151416779L;
	/**
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号")
	private String phone;

}
