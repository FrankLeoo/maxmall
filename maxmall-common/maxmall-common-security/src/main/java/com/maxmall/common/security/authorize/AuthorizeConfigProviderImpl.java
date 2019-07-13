package com.maxmall.common.security.authorize;

import com.maxmall.common.security.properties.SecurityConstants;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * 核心模块的授权配置提供器，安全模块涉及的url的授权配置在这里。
 *
 * @author maxmall.net@gmail.com
 */
@Component
@Order(Integer.MIN_VALUE)
public class AuthorizeConfigProviderImpl implements AuthorizeConfigProvider {

	/**
	 * Config boolean.
	 *
	 * @param config the config
	 *
	 * @return the boolean
	 */
	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		config.antMatchers(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
				"/druid/**", "/auth/**", "/api/**","/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs").permitAll()
				.anyRequest().authenticated();

		return false;
	}

}
