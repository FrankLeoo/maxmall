package com.maxmall.common.security.interceptor;

import com.maxmall.common.security.code.ValidateCodeException;
import com.maxmall.common.security.code.ValidateCodeProcessorHolder;
import com.maxmall.common.security.code.ValidateCodeType;
import com.maxmall.common.security.properties.SecurityConstants;
import com.maxmall.common.security.properties.SecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 跨域拦截器
 *
 * @author nickliu 2017-07-26 17:19
 */
@Component("crossInFilter")
public class CrossInFilter extends OncePerRequestFilter implements InitializingBean {

    /**
     * 系统配置信息
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * Do filter internal.
     *
     * @param request  the request
     * @param response the response
     * @param chain    the chain
     *
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String accessControlDomain = securityProperties.getAccessControlDomain();

        String origin = request.getHeader("origin");
        response.addHeader("Access-Control-Allow-Origin", StringUtils.isNotBlank(accessControlDomain) && !"*".equals(accessControlDomain) ? accessControlDomain : origin);
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.addHeader("Access-Control-Max-Age", "1800");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type,x-token,Authorization");

        chain.doFilter(request, response);

    }


}
