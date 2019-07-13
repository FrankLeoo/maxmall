package com.maxmall.provider.merchant.service.impl;

import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.core.utils.RequestUtil;
import com.maxmall.common.security.SecurityUtils;
import com.maxmall.common.security.authorize.JwtTokenManager;
import com.maxmall.common.security.authorize.demain.AuthorAccessToken;
import com.maxmall.common.util.RedisKeyUtil;
import com.maxmall.provider.merchant.exception.UacBizException;
import com.maxmall.provider.merchant.model.domain.account.AccountDO;
import com.maxmall.provider.merchant.model.enums.UserTokenStatusEnum;
import com.maxmall.provider.merchant.service.AccountService;
import com.maxmall.provider.merchant.service.UacUserTokenService;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * The class Uac user token service.
 *
 * @author maxmall.net@gmail.com
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UacUserTokenServiceImpl implements UacUserTokenService {

	@Autowired
	private AccountService accountService;
	@Autowired
	private JwtTokenManager tokenManager;
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	@Value("${jwt.access_token_expiration}")
	private Integer access_token_expiration;

	@Override
	public void saveUserToken(String accessToken, String refreshToken, UserTokenDto loginAuthDto, HttpServletRequest request) {
		// 获取登录时间
		Long userId = loginAuthDto.getUserId();
		AccountDO uacUser = accountService.selectByKey(userId);
		final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
		//获取客户端操作系统
		final String os = userAgent.getOperatingSystem().getName();
		//获取客户端浏览器
		final String browser = userAgent.getBrowser().getName();
		final String remoteAddr = RequestUtil.getRemoteAddr(request);
		// 根据IP获取位置信息
		String remoteLocation = "未知";

		// 存入mysql数据库
		UserTokenDto tokenDto = new UserTokenDto();
		tokenDto.setOs(os);
		tokenDto.setBrowser(browser);
		tokenDto.setAccessToken(accessToken);
		tokenDto.setLoginIp(remoteAddr);
		tokenDto.setLoginLocation(remoteLocation);
		tokenDto.setLoginTime(uacUser.getLastLoginTime());
		tokenDto.setLoginName(loginAuthDto.getLoginName());
		tokenDto.setRefreshToken(refreshToken);
		tokenDto.setStatus(UserTokenStatusEnum.ON_LINE.getStatus());
		tokenDto.setUserId(userId);
		tokenDto.setUserName(loginAuthDto.getUserName());
		// 存入redis数据库
		redisTemplate.opsForValue().set(RedisKeyUtil.getAccessTokenKey(accessToken), tokenDto, access_token_expiration, TimeUnit.SECONDS);
	}

	@Override
	public UserTokenDto getByAccessToken(String accessToken) {

		UserTokenDto userTokenDto = (UserTokenDto) redisTemplate.opsForValue().get(RedisKeyUtil.getAccessTokenKey(accessToken));
		return userTokenDto;
	}

	@Override
	public AuthorAccessToken refreshToken(String accessToken, String refreshToken, HttpServletRequest request) throws HttpProcessException {
		Map<String, Object> map = new HashMap<>(2);
		map.put("grant_type", "refresh_token");
		map.put("refresh_token", refreshToken);

		// 更新本次token数据
		UserTokenDto tokenDto = this.getByAccessToken(accessToken);
		if (tokenDto == null){
			throw new UacBizException(ErrorCodeEnum.UAC10011002);
		}
		tokenDto.setStatus(UserTokenStatusEnum.ON_REFRESH.getStatus());
		AccountDO uacUser = accountService.findByLoginName(tokenDto.getLoginName());

		UserTokenDto loginAuthDto = new UserTokenDto(uacUser.getId(), uacUser.getLoginName(), uacUser.getUserName());
		this.updateUacUserToken(tokenDto, loginAuthDto);

		UserDetails principal = SecurityUtils.getCurrentUser();
		// 创建刷新token
		AuthorAccessToken refreshTokenNew = tokenManager.generateAccessToken(principal);

		this.saveUserToken(refreshTokenNew.getAccess_token(), refreshTokenNew.getRefresh_token(), loginAuthDto, request);
		return refreshTokenNew;
	}

	@Override
	public void updateUacUserToken(UserTokenDto tokenDto, UserTokenDto loginAuthDto) {
		redisTemplate.opsForValue().set(RedisKeyUtil.getAccessTokenKey(tokenDto.getAccessToken()), tokenDto, access_token_expiration, TimeUnit.SECONDS);
	}

}
