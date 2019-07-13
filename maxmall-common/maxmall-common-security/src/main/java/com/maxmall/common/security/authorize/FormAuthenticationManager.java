package com.maxmall.common.security.authorize;

import com.maxmall.common.security.authorize.handler.AuthenticationFailureHandler;
import com.maxmall.common.security.authorize.handler.AuthenticationSuccessHandler;
import com.maxmall.common.security.authorize.handler.PcLogoutSuccessHandler;
import com.maxmall.common.security.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

/**
 * 表单登录配置
 *
 * @author maxmall.net@gmail.com
 */
@Component
public class FormAuthenticationManager {

	/**
	 * The Pc authentication success handler.
	 */
	protected final AuthenticationSuccessHandler authenticationSuccessHandler;
	/**
	 * The Pc authentication failure handler.
	 */
	protected final AuthenticationFailureHandler authenticationFailureHandler;
	/**
	 * The Pc authentication success handler.
	 */
	protected final PcLogoutSuccessHandler pcLogoutSuccessHandler;

	/**
	 * Instantiates a new Form authentication config.
	 *
	 * @param authenticationSuccessHandler the pc authentication success handler
	 * @param authenticationFailureHandler the pc authentication failure handler
	 */
	@Autowired
	public FormAuthenticationManager(AuthenticationSuccessHandler authenticationSuccessHandler,
									 AuthenticationFailureHandler authenticationFailureHandler,
									 PcLogoutSuccessHandler pcLogoutSuccessHandler) {
		this.authenticationSuccessHandler = authenticationSuccessHandler;
		this.authenticationFailureHandler = authenticationFailureHandler;
		this.pcLogoutSuccessHandler = pcLogoutSuccessHandler;
	}

	/**
	 * Configure.
	 * @param http the http
	 *
	 * @throws Exception the exception
	 */
	public void configure(HttpSecurity http) throws Exception {
		http.formLogin()
				.loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
				.usernameParameter("username")
				.passwordParameter("password")
				.successHandler(authenticationSuccessHandler)
				.failureHandler(authenticationFailureHandler)
				.and()
				.logout()
				//退出系统url
				.logoutUrl(SecurityConstants.DEFAULT_LOGOUT_PROCESSING_URL)
				//退出系统后的 业务处理
				.logoutSuccessHandler(pcLogoutSuccessHandler);
	}

}
