
package com.maxmall.provider.merchant.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * The class Role bind user dto.
 *
 * @author maxmall.net@gmail.com
 */
@Data
@ApiModel(value = "角色绑定用户")
public class UserBindRoleVo implements Serializable {

	private static final long serialVersionUID = -2521583668470612548L;

	/**
	 * 角色ID
	 */
	private Long id;
	/**
	 * 角色编号.
	 */
	private String code;

	/**
	 * 角色名称.
	 */
	private String name;

	/**
	 * 角色描述.
	 */
	private String description;

	/**
	 * 是否绑定
	 */
	private boolean binded;
}
