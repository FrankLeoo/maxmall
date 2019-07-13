package com.maxmall.common.core.config;

import com.maxmall.common.config.properties.MaxmallProperties;
import com.maxmall.common.config.properties.SwaggerProperties;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

/**
 * The class Swagger configuration.
 *
 * @author maxmall.net@gmail.com
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@Resource
	private MaxmallProperties maxmallProperties;

	/**
	 * Reservation api docket.
	 *
	 * @return the docket
	 */
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				.paths(PathSelectors.any())
				.build().enable(true);
	}

	private ApiInfo apiInfo() {
		SwaggerProperties swagger = maxmallProperties.getSwagger();
		return new ApiInfoBuilder()
				.title(swagger.getTitle())
				.description(swagger.getDescription())
				.version(swagger.getVersion())
				.license(swagger.getLicense())
				.licenseUrl(swagger.getLicenseUrl())
				.contact(new Contact(swagger.getContactName(), swagger.getContactUrl(), swagger.getContactEmail()))
				.build();
	}
}