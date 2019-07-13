package com.maxmall.common.security.authorize.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxmall.common.security.authorize.JwtTokenManager;
import com.maxmall.common.util.RedisKeyUtil;
import com.maxmall.common.util.wrapper.WrapMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * APP环境下认证成功处理器.
 *
 * @author maxmall.net@gmail.com
 */
@Component("pcLogoutSuccessHandler")
@Slf4j
public class PcLogoutSuccessHandler implements LogoutSuccessHandler {

	@Autowired
	private JwtTokenManager tokenManager;
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	@Resource
	private ObjectMapper objectMapper;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		//删除key
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (StringUtils.isNotEmpty(token)){
			String key = RedisKeyUtil.getAccessTokenKey(token);
			redisTemplate.delete(key);
		}
		tokenManager.removeToken(token);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write((objectMapper.writeValueAsString(WrapMapper.ok("退出成功"))));
	}
}
