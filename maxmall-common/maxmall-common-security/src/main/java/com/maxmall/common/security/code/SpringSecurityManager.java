package com.maxmall.common.security.code;

import com.maxmall.common.security.interceptor.CrossInFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

/**
 * 校验码相关安全配置
 *
 * @author maxmall.net@gmail.com
 */
@Component("validateCodeSecurityConfig")
public class SpringSecurityManager extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	@Autowired
	private CrossInFilter crossInFilter;
	@Autowired
	private ValidateCodeFilter validateCodeFilter;

	/**
	 * Configure.
	 *
	 * @param http the http
	 */
	@Override
	public void configure(HttpSecurity http) {
		http.addFilterBefore(crossInFilter,AbstractPreAuthenticatedProcessingFilter.class).
				addFilterBefore(validateCodeFilter, AbstractPreAuthenticatedProcessingFilter.class);
	}

}
