package com.maxmall.common.security.authorize.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.util.wrapper.WrapMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ivoter
 * @ClassName JsonAuthenticationEntryPoint.java
 * @date 2019/04/29 18:17:00
 * @Description 认证失败后json返回
 */
public class JsonAuthenticationEntryPoint implements AuthenticationEntryPoint, InitializingBean {

    private static final Log logger = LogFactory.getLog(JsonAuthenticationEntryPoint.class);

    @Resource
    private ObjectMapper objectMapper;

    /**
     * Performs the redirect (or forward) to the login form URL.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(
                WrapMapper.error(ErrorCodeEnum.UAC10010002.getCode(),ErrorCodeEnum.UAC10010002.getMsg())
        ));
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
