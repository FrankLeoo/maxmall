package com.maxmall.provider.product.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The class O auth 2 feign auto configuration.
 *
 * @author maxmall.net @gmail.com
 */
@Configuration
public class FeignAutoConfiguration {


	/**
	 * feign request interceptor .
	 * 配置jwt token到header中
	 *
	 * @return the request interceptor
	 */
	@Bean
	public RequestInterceptor oauth2FeignRequestInterceptor() {
		return new FeignRequestInterceptor();
	}

}