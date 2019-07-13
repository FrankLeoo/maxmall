package com.maxmall.provider.merchant.model.vo;

import com.maxmall.common.base.dto.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The class Role vo.
 *
 * @author maxmall.net@gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleVo extends BaseVo {

	private static final long serialVersionUID = 3819529748816533170L;

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
	 * 排序.
	 */
	private Integer sort;

	/**
	 * 状态.
	 */
	private Integer status;
}