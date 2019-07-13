package com.maxmall.provider.merchant.web.portal;

import com.google.common.base.Preconditions;
import com.maxmall.common.base.dto.CheckValidDto;
import com.maxmall.common.base.dto.ResetPhoneDto;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.core.support.BaseController;
import com.maxmall.common.security.authorize.JwtTokenManager;
import com.maxmall.common.security.authorize.demain.AuthorAccessToken;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.merchant.exception.UacBizException;
import com.maxmall.provider.merchant.model.constant.UacApiConstant;
import com.maxmall.provider.merchant.model.dto.account.AccountRegisterDto;
import com.maxmall.provider.merchant.model.dto.user.ResetLoginPwdDto;
import com.maxmall.provider.merchant.service.AccountService;
import com.maxmall.provider.merchant.service.SmsService;
import com.maxmall.provider.merchant.service.UacUserTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 不认证的URL请求.
 *
 * @author maxmall.net@gmail.com
 */
@RestController
@RequestMapping(value = "/auth")
@Api(value = "Web-AuthRestController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AuthRestController extends BaseController {
	@Resource
	private AccountService accountService;
	@Resource
	private SmsService smsService;
	@Resource
	private UacUserTokenService uacUserTokenService;

	/**
	 * 校验数据.
	 *
	 * @param checkValidDto the check valid dto
	 * @return the wrapper
	 */
	@PostMapping(value = "/checkValid")
	@ApiOperation(httpMethod = "POST", value = "校验数据")
	public Wrapper<String> checkValid(@Validated @RequestBody CheckValidDto checkValidDto) {
		String type = checkValidDto.getType();
		String validValue = checkValidDto.getValidValue();

		Preconditions.checkArgument(StringUtils.isNotEmpty(validValue), "参数错误");
		boolean result = false;
		//开始校验
		if (UacApiConstant.Valid.LOGIN_NAME.equals(type)) {
			result = accountService.checkLoginName(validValue);
			if (!result) {
				return WrapMapper.error("用户名已存在");
			}
            return WrapMapper.ok("验证成功");
		}
		if (UacApiConstant.Valid.PHONE.equals(type)) {
			result = accountService.checkPhone(validValue);
			if (!result) {
				return WrapMapper.error("手机号码已存在");
			}
            return WrapMapper.ok("验证成功");
		}
		return WrapMapper.error(ErrorCodeEnum.UAC10011017);
	}

	/**
	 * 重置密码-手机-提交.
	 *
	 * @param resetPhoneDto   the mobile
	 * @return the wrapper
	 */
	@PostMapping(value = "/resetPwdPhone")
	@ApiOperation(httpMethod = "POST", value = "重置密码-手机-提交")
	public Wrapper<String> resetPwdPhone(@Validated @RequestBody ResetPhoneDto resetPhoneDto, ServletWebRequest request) {
		logger.info("重置密码-手机-提交, mobile={}", resetPhoneDto.getPhone());

		String token = smsService.submitResetPwdPhone(resetPhoneDto.getPhone(),request);
		return WrapMapper.ok("验证码发送成功");
	}

	/**
	 * 重置密码
	 *
	 * @param resetLoginPwdDto the reset login pwd dto
	 * @return the wrapper
	 */
	@PostMapping(value = "/resetLoginPwd")
	@ApiOperation(httpMethod = "POST", value = "重置密码-最终提交")
	public Wrapper<Boolean> resetLoginPwd(@Validated @RequestBody ResetLoginPwdDto resetLoginPwdDto) {
		accountService.resetLoginPwd(resetLoginPwdDto);
		return WrapMapper.ok();
	}

	/**
	 * 注册用户.注册成功后需要添加申请商户
	 * @param user the user
	 * @return the wrapper
	 */
	@PostMapping(value = "/register")
	@ApiOperation(httpMethod = "POST", value = "注册用户")
	public Wrapper<Integer> registerUser(@Validated @RequestBody AccountRegisterDto user) {
		int row = accountService.register(user);
		return WrapMapper.ok(row);
	}

	/**
	 * 刷新token.
	 *
	 * @param request      the request
	 * @param refreshToken the refresh token
	 * @param accessToken  the access token
	 * @return the wrapper
	 */
	@GetMapping(value = "/account/refreshToken")
	@ApiOperation(httpMethod = "POST", value = "刷新token")
	public Wrapper<AuthorAccessToken> refreshToken(HttpServletRequest request, @RequestParam(value = "refreshToken") String refreshToken, @RequestParam(value = "accessToken") String accessToken) {
		AuthorAccessToken token;
		try {

			Preconditions.checkArgument(org.apache.commons.lang3.StringUtils.isNotEmpty(accessToken), "accessToken is null");
			Preconditions.checkArgument(org.apache.commons.lang3.StringUtils.isNotEmpty(refreshToken), "refreshToken is null");
			String header = request.getHeader(HttpHeaders.AUTHORIZATION);
			if (header == null || !header.startsWith(JwtTokenManager.BEARER_TOKEN_TYPE)) {
				throw new UacBizException(ErrorCodeEnum.UAC10010002);
			}
			token = uacUserTokenService.refreshToken(accessToken, refreshToken, request);
		} catch (Exception e) {
			logger.error("refreshToken={}", e.getMessage(), e);
			return WrapMapper.error();
		}
		return WrapMapper.ok(token);
	}
}
