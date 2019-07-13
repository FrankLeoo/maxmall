package com.maxmall.common.security.authorize.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.utils.RequestUtil;
import com.maxmall.common.security.SecurityUser;
import com.maxmall.common.security.authorize.JwtTokenManager;
import com.maxmall.common.security.authorize.demain.AuthorAccessToken;
import com.maxmall.common.util.RedisKeyUtil;
import com.maxmall.common.util.wrapper.WrapMapper;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * APP环境下认证成功处理器.
 *
 * @author maxmall.net@gmail.com
 */
@Component("pcAuthenticationSuccessHandler")
@Slf4j
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Resource
    private ObjectMapper objectMapper;
    @Autowired
    private JwtTokenManager tokenManager;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Value("${jwt.access_token_expiration}")
    private Integer access_token_expiration;
    @Value("${jwt.refresh_token_expiration}")
    private Integer refresh_token_expiration;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        SecurityUser principal = (SecurityUser) authentication.getPrincipal();
        //配置生成token 存储redis
        AuthorAccessToken token = tokenManager.generateAccessToken(principal);
        //将登录人信息放在redis中
        this.handlerLoginData(token, principal, request);

        log.info("用户【 {} 】记录登录日志", principal.getUsername());

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write((objectMapper.writeValueAsString(WrapMapper.ok(token))));

    }

    private void handlerLoginData(AuthorAccessToken token, final SecurityUser principal, HttpServletRequest request) {

        final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        //获取客户端操作系统
        final String os = userAgent.getOperatingSystem().getName();
        //获取客户端浏览器
        final String browser = userAgent.getBrowser().getName();
        final String remoteAddr = RequestUtil.getRemoteAddr(request);
        // 根据IP获取位置信息
        String remoteLocation = "未知";
        String accessToken = token.getAccess_token();
        String refreshToken = token.getRefresh_token();

        UserTokenDto loginAuthDto = new UserTokenDto(principal.getUserId(), principal.getLoginName(), principal.getNickName());
        loginAuthDto.setMerchantId(principal.getMerchantId());
        loginAuthDto.setMerchantSn(principal.getMerchantSn());
        loginAuthDto.setMerchantName(principal.getMerchantName());
        loginAuthDto.setIsMaster(principal.getIsMaster());

        loginAuthDto.setOs(os);
        loginAuthDto.setBrowser(browser);
        loginAuthDto.setAccessToken(accessToken);
        loginAuthDto.setRefreshToken(refreshToken);
        loginAuthDto.setAccessTokenValidity(access_token_expiration);
        loginAuthDto.setLoginIp(remoteAddr);
        loginAuthDto.setLoginLocation(remoteLocation);
        loginAuthDto.setLoginTime(new Date());

        loginAuthDto.setRefreshTokenValidity(refresh_token_expiration);
        loginAuthDto.setStatus(0);

        redisTemplate.opsForValue().set(RedisKeyUtil.getAccessTokenKey(accessToken), loginAuthDto, access_token_expiration, TimeUnit.SECONDS);
    }
}
