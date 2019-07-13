package com.maxmall.provider.merchant.web.portal;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.annotation.LogAnnotation;
import com.maxmall.common.core.support.BaseController;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.merchant.model.dto.user.*;
import com.maxmall.provider.merchant.model.vo.AccountVo;
import com.maxmall.provider.merchant.model.vo.UserBindRoleVo;
import com.maxmall.provider.merchant.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户管理主页面.
 *
 * @author maxmall.net@gmail.com
 */
@RestController
@RequestMapping(value = GlobalConstant.PORTAL_PATH+"/account", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Web - AccountMainController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AccountMainController extends BaseController {
	@Resource
	private AccountService accountService;

	/**
	 * 查询子用户列表.
	 *
	 * @param queryUserDto the uac user
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/queryListWithPage")
	@ApiOperation(httpMethod = "POST", value = "查询子用户列表")
	public Wrapper<PageInfo<AccountVo>> queryUserListWithPage(@Validated @RequestBody QueryUserDto queryUserDto) {
		UserTokenDto loginAuthDto = getLoginAuthDto();

		logger.info("查询用户列表uacUser={}", queryUserDto);
		PageInfo<AccountVo> pageInfo = accountService.queryUserListWithPage(queryUserDto,loginAuthDto);
		return WrapMapper.ok(pageInfo);
	}

	/**
	 * 新增用户
	 *
	 * @param userModifyDto the user
	 *
	 * @return the wrapper
	 */
	@LogAnnotation
	@PostMapping(value = "/save")
	@ApiOperation(httpMethod = "POST", value = "新增用户")
	public Wrapper<Integer> saveUser(@Validated @RequestBody UserModifyDto userModifyDto) {
		logger.info(" 新增用户或更新 user={}", userModifyDto);
		UserTokenDto loginAuthDto = getLoginAuthDto();

		int row = accountService.saveOrModifyUser(userModifyDto, loginAuthDto);
		return WrapMapper.ok(row);
	}

	/**
	 * 根据Id修改用户状态.
	 *
	 * @param modifyUserStatusDto the modify user status dto
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/modifyUserStatusById")
	@LogAnnotation
	@ApiOperation(httpMethod = "POST", value = "根据Id修改用户状态")
	public Wrapper<Integer> modifyUserStatusById(@Validated @RequestBody ModifyUserStatusDto modifyUserStatusDto) {
		logger.info(" 根据Id修改用户状态 modifyUserStatusDto={}", modifyUserStatusDto);
		UserTokenDto loginAuthDto = getLoginAuthDto();

		int result = accountService.modifyUserStatusById(modifyUserStatusDto, loginAuthDto);
		return handleResult(result);
	}

	/**
	 * 通过Id删除用户.
	 *
	 * @param userId the user id
	 *
	 * @return the wrapper
	 */
	@LogAnnotation
	@PostMapping(value = "/deleteUserById/{userId}")
	@ApiOperation(httpMethod = "POST", value = "通过Id删除用户")
	public Wrapper<Integer> deleteUserById(@Validated @PathVariable Long userId) {
		logger.info(" 通过Id删除用户 userId={}", userId);
		UserTokenDto loginAuthDto = getLoginAuthDto();

		int result = accountService.deleteUserById(userId,loginAuthDto);
		return handleResult(result);
	}

	/**
	 * 获取用户绑定角色页面数据.
	 *
	 * @param userId the user id
	 *
	 * @return the bind role
	 */
	@PostMapping(value = "/getBindRole/{userId}")
	@ApiOperation(httpMethod = "POST", value = "获取用户绑定角色页面数据")
	public Wrapper<List<UserBindRoleVo>> getBindRole(@ApiParam(name = "userId", value = "用户id") @PathVariable Long userId) {
		logger.info("获取用户绑定角色页面数据. userId={}", userId);

		List<UserBindRoleVo> bindUserDto = accountService.getUserBindRoleDto(userId);
		return WrapMapper.ok(bindUserDto);
	}

	/**
	 * 用户绑定角色.
	 *
	 * @param bindUserRolesDto the bind user roles dto
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/bindRole")
	@LogAnnotation
	@ApiOperation(httpMethod = "POST", value = "用户绑定角色")
	public Wrapper<Integer> bindUserRoles(@Validated @RequestBody BindUserRolesDto bindUserRolesDto) {
		logger.info("用户绑定角色 bindUserRolesDto={}", bindUserRolesDto);
		UserTokenDto loginAuthDto = getLoginAuthDto();

		int row = accountService.bindUserRoles(bindUserRolesDto, loginAuthDto);
		return WrapMapper.ok(row);
	}

	/**
	 * 用户修改密码
	 *
	 * @param userModifyPwdDto the user modify pwd dto
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/modifyUserPwd")
	@LogAnnotation
	@ApiOperation(httpMethod = "POST", value = "用户修改密码")
	public Wrapper<Integer> modifyUserPwd(@Validated @RequestBody UserModifyPwdDto userModifyPwdDto) {
		logger.info("==》vue用户修改密码, userModifyPwdDto={}", userModifyPwdDto);

		UserTokenDto loginAuthDto = getLoginAuthDto();
		int result = accountService.userModifyPwd(userModifyPwdDto, loginAuthDto);
		return handleResult(result);
	}

	/**
	 * 根据用户Id查询用户信息.
	 *
	 * @param userId the user id
	 * @return the uac user by id
	 */
	@PostMapping(value = "/getUserById/{userId}")
	@ApiOperation(httpMethod = "POST", value = "根据用户Id查询用户信息")
	public Wrapper<AccountVo> getUserById(@ApiParam(name = "userId", value = "用户ID") @PathVariable Long userId) {
		logger.info("getUacUserById - 根据用户Id查询用户信息. userId={}", userId);
		UserTokenDto loginAuthDto = getLoginAuthDto();
		AccountVo uacUser = accountService.queryByUserId(userId,loginAuthDto);

		logger.info("getUacUserById - 根据用户Id查询用户信息. [OK] uacUser={}", uacUser);
		return WrapMapper.ok(uacUser);
	}


}
