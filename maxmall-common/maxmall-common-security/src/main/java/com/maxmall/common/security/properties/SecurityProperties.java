package com.maxmall.common.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * The class Security properties.
 *
 * @author maxmall.net@gmail.com
 */
@ConfigurationProperties(prefix = "maxmall.security")
public class SecurityProperties {

	/**
	 * 浏览器环境配置
	 */
	private BrowserProperties browser = new BrowserProperties();
	/**
	 * 验证码配置
	 */
	private ValidateCodeProperties code = new ValidateCodeProperties();

	private String accessControlDomain;

	/**
	 * Gets browser.
	 *
	 * @return the browser
	 */
	public BrowserProperties getBrowser() {
		return browser;
	}

	/**
	 * Sets browser.
	 *
	 * @param browser the browser
	 */
	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}

	/**
	 * Gets code.
	 *
	 * @return the code
	 */
	public ValidateCodeProperties getCode() {
		return code;
	}

	/**
	 * Sets code.
	 *
	 * @param code the code
	 */
	public void setCode(ValidateCodeProperties code) {
		this.code = code;
	}

	public String getAccessControlDomain() {
		return accessControlDomain;
	}

	public void setAccessControlDomain(String accessControlDomain) {
		this.accessControlDomain = accessControlDomain;
	}
}

