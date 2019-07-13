package com.maxmall.provider.product.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * The class O auth 2 feign request interceptor.
 *
 * @author maxmall.net @gmail.com
 */
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {

	/**
	 * Apply.
	 *
	 * @param template the template
	 */
	@Override
	public void apply(RequestTemplate template) {

		HttpServletRequest request = getServletRequest();
		if (null == request){
			return;
		}
		template.header(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
	}

	private HttpServletRequest getServletRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}

}
