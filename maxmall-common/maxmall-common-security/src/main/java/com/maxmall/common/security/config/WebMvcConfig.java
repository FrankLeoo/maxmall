package com.maxmall.common.security.config;

import com.maxmall.common.security.interceptor.MvcObjectMapper;
import com.maxmall.common.security.interceptor.TokenInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import java.util.List;

/**
 * The class Web mvc config.
 *
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Resource(name="tokenInterceptor")
	private TokenInterceptor tokenInterceptor;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(tokenInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/druid/**", "/auth/**", "/api/**","/swagger-resources/**", "*.js", "/**/*.js", "*.css", "/**/*.css", "*.html", "/**/*.html");
		// 参数拦截器
		super.addInterceptors(registry);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		MvcObjectMapper.buidMvcMessageConverter(converters);
	}

	@Bean
	@ConditionalOnMissingBean(HandlerInterceptor.class)
	@ConditionalOnProperty(prefix = "maxmall.token.interceptor", name = "enable", havingValue = "true")
	public TokenInterceptor tokenInterceptor() {
		return new TokenInterceptor();
	}
}
