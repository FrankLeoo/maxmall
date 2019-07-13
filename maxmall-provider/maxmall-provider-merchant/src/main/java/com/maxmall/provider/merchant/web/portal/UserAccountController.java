package com.maxmall.provider.merchant.web.portal;

import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.BaseController;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.merchant.model.dto.user.LoginRespDto;
import com.maxmall.provider.merchant.model.enums.UserTokenStatusEnum;
import com.maxmall.provider.merchant.model.vo.AccountInfoVo;
import com.maxmall.provider.merchant.service.AccountService;
import com.maxmall.provider.merchant.service.UacUserTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 商户平台登录相关.
 *
 * @author maxmall.net@gmail.com
 */
@RestController
@RequestMapping(value = GlobalConstant.PORTAL_PATH+"/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Web - UserAccountController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserAccountController extends BaseController {

	@Resource
	private AccountService accountService;
	@Resource
	private UacUserTokenService uacUserTokenService;

	/**
	 * 登录成功获取菜单信息和用户信息.
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/menus")
	@ApiOperation(httpMethod = "POST", value = "登录成功获取用户菜单")
	public Wrapper<LoginRespDto> accountMenus() {

        UserTokenDto userTokenDto = getLoginAuthDto();
        LoginRespDto result = accountService.findMenusByAccount(userTokenDto);
		return WrapMapper.ok(result);
	}

	/**
	 * 登出.
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/logout")
	@ApiOperation(httpMethod = "POST", value = "登出")
	public Wrapper loginAfter() {
		// 修改用户在线状态
		UserTokenDto userTokenDto = getLoginAuthDto();
		userTokenDto.setStatus(UserTokenStatusEnum.OFF_LINE.getStatus());
		uacUserTokenService.updateUacUserToken(userTokenDto, getLoginAuthDto());
		return WrapMapper.ok();
	}

	/**
	 * 登录成功获取菜单信息和用户信息.
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/info")
	@ApiOperation(httpMethod = "POST", value = "登录成功获取用户菜单")
	public Wrapper<AccountInfoVo> accountInfo() {

		UserTokenDto userTokenDto = getLoginAuthDto();
		AccountInfoVo result = accountService.accountInfo(userTokenDto);
		//获取用户角色详情
		return WrapMapper.ok(result);
	}

}