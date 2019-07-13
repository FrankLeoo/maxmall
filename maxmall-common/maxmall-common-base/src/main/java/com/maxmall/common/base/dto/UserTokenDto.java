package com.maxmall.common.base.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * The class Uac user token dto.
 *
 * @author maxmall.net @gmail.com
 */
@Data
public class UserTokenDto implements Serializable {

	private static final long serialVersionUID = -1137852221455042256L;

	private Long userId;

	/**
	 * 登陆人Ip地址
	 */
	private String loginIp;

	/**
	 * 登录地址
	 */
	private String loginLocation;

	/**
	 * 操作系统
	 */
	private String os;

	/**
	 * 浏览器类型
	 */
	private String browser;

	/**
	 * 访问token
	 */
	private String accessToken;

	/**
	 * 刷新token
	 */
	private String refreshToken;

	/**
	 * 访问token的生效时间(秒)
	 */
	private Integer accessTokenValidity;

	/**
	 * 刷新token的生效时间(秒)
	 */
	private Integer refreshTokenValidity;

	/**
	 * 0 在线 10已刷新 20 离线
	 */
	private Integer status;

	//用户信息
	private String loginName;

	private String userName;

	private Date loginTime;

	/**
	 * 是否主账户.
	 */
	private Boolean isMaster;

	//商户信息
	/**
	 * 商户id.
	 */
	private Long merchantId;
	/**
	 * 商户名称.
	 */
	private String merchantName;
	/**
	 * 商户编号.
	 */
	private String merchantSn;

	public UserTokenDto() {
	}

	public UserTokenDto(Long userId, String loginName, String userName) {
		this.userId = userId;
		this.loginName = loginName;
		this.userName = userName;
	}

}
