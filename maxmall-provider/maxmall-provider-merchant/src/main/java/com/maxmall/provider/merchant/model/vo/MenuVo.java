package com.maxmall.provider.merchant.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * The class Menu vo.
 *
 * @author maxmall.net@gmail.com
 */
@Data
@ApiModel("菜单")
public class MenuVo implements Serializable {
	/**
	 * serialVersionUID:用一句话描述这个变量表示什么.
	 *
	 * @since JDK 1.7
	 */
	private static final long serialVersionUID = -2099147126084213856L;

	/**
	 * menu.id;
	 */
	private Long id;

	/**
	 * 父id
	 */
	private Long pid;

	/**
	 * 菜单编码
	 */
	private String menuCode;

	/**
	 * 菜单编码
	 */
	private String permission;

	/**
	 * 菜单名称
	 */
	private String menuName;

	/**
	 * 菜单URL
	 */
	private String url;
	/**
	 * 图标
	 */
	private String icon;

	/**
	 * 序号
	 */
	private String sort;

	private MenuVo parentMenu;

	private List<MenuVo> subMenu;

	private boolean hasMenu = false;

}