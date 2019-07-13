package com.maxmall.common.security.properties;

import lombok.Data;

/**
 * The class Email code properties.
 *
 * @author maxmall.net@gmail.com
 */
@Data
public class EmailCodeProperties {

	/**
	 * 过期时间
	 */
	private int expireIn = 60 * 60 * 24;
	/**
	 * 要拦截的url，多个url用逗号隔开，ant pattern
	 */
	private String url;

}
