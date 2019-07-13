
package com.maxmall.provider.merchant.service;

import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.security.authorize.demain.AuthorAccessToken;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录jwt token 管理.
 *
 * @author maxmall.net@gmail.com
 */
public interface UacUserTokenService {

	/**
	 * 保存
	 *
	 * @param accessToken the access token
	 *
	 * @return the by access token
	 */
	void saveUserToken(String accessToken, String refreshToken, UserTokenDto loginAuthDto, HttpServletRequest request);

	/**
	 * 获取token.
	 *
	 * @param accessToken the access token
	 *
	 * @return the by access token
	 */
	UserTokenDto getByAccessToken(String accessToken);


	/**
	 * 更新token.
	 *
	 * @param tokenDto     the token dto
	 * @param loginAuthDto the login auth dto
	 */
	void updateUacUserToken(UserTokenDto tokenDto, UserTokenDto loginAuthDto);

	/**
	 * 刷新token.
	 *
	 * @param accessToken  the access token
	 * @param refreshToken the refresh token
	 * @param request      the request
	 *
	 * @return the string
	 *
	 * @throws HttpProcessException the http process exception
	 */
	AuthorAccessToken refreshToken(String accessToken, String refreshToken, HttpServletRequest request) throws HttpProcessException;

}
