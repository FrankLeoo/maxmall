package com.maxmall.common.security.controller;

import com.maxmall.common.security.SecurityResult;
import com.maxmall.common.security.code.ValidateCodeException;
import com.maxmall.common.security.code.ValidateCodeProcessor;
import com.maxmall.common.security.code.ValidateCodeProcessorHolder;
import com.maxmall.common.security.properties.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 生成校验码的请求处理器
 *
 * @author maxmall.net@gmail.com
 */
@Slf4j
@RestController
public class ValidateCodeController {

	@Resource
	private ValidateCodeProcessorHolder validateCodeProcessorHolder;

	/**
	 * 创建验证码，根据验证码类型不同，调用不同的 {@link ValidateCodeProcessor}接口实现
	 *
	 * @param request  the request
	 * @param response the response
	 * @param type     the type
	 *
	 * @throws Exception the exception
	 */
	@PostMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
	public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
		validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
	}

	/**
	 * Check code object.
	 *
	 * @param request  the request
	 * @param response the response
	 * @param type     the type
	 *
	 * @return the object
	 */
	@GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
	public SecurityResult checkCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) {
		SecurityResult result = new SecurityResult(SecurityResult.SUCCESS_CODE, "校验成功", true);
		try {
			validateCodeProcessorHolder.findValidateCodeProcessor(type).check(new ServletWebRequest(request, response));
		} catch (ValidateCodeException e) {
			result = SecurityResult.error(e.getMessage(), false);
		} catch (Exception e) {
			log.error("getAccessToken={}", e.getMessage(), e);
			result = SecurityResult.error("验证码错误", false);
		}
		return result;
	}
}
