package com.maxmall.provider.merchant.web.portal;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.annotation.LogAnnotation;
import com.maxmall.common.core.support.BaseController;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.merchant.model.dto.role.RoleBindUserDto;
import com.maxmall.provider.merchant.model.dto.role.RoleBindUserReqDto;
import com.maxmall.provider.merchant.model.dto.user.QueryRoleDto;
import com.maxmall.provider.merchant.model.vo.MenuVo;
import com.maxmall.provider.merchant.model.vo.RoleVo;
import com.maxmall.provider.merchant.service.MenuService;
import com.maxmall.provider.merchant.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色管理-公共方法.
 *
 * @author maxmall.net@gmail.com
 */
@RestController
@RequestMapping(value = GlobalConstant.PORTAL_PATH + "/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Web - RoleMainController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RoleMainController extends BaseController {

    @Resource
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    /**
     * 分页查询角色信息.
     *
     * @param queryRoleDto the role
     *
     * @return the wrapper
     */
    @PostMapping(value = "/queryRoleListWithPage")
    @ApiOperation(httpMethod = "POST", value = "查询角色列表")
    public Wrapper<PageInfo<RoleVo>> queryUacRoleListWithPage(@Validated @RequestBody QueryRoleDto queryRoleDto) {

        logger.info("查询角色列表roleQuery={}", queryRoleDto);

        PageInfo<RoleVo> roleVoList = roleService.queryRoleListWithPage(queryRoleDto);
        return WrapMapper.ok(roleVoList);
    }

    /**
     * 查看角色信息.
     *
     * @param roleId the id
     * @return the wrapper
     */
    @PostMapping(value = "/queryRoleInfoById/{roleId}")
    @ApiOperation(httpMethod = "POST", value = "查看角色信息")
    public Wrapper<RoleVo> queryRoleInfo(@PathVariable Long roleId) {
        RoleVo role = roleService.queryRoleInfo(roleId);
        return WrapMapper.ok(role);
    }

    /**
     * 获取菜单树.
     *
     * @param roleId the role id
     * @return the menu tree by role id
     */
    @PostMapping(value = "/getMenuTreeByRoleId/{roleId}")
    @ApiOperation(httpMethod = "POST", value = "获取菜单树")
    public Wrapper<List<MenuVo>> getMenuTreeByRoleId(@ApiParam(name = "roleId", value = "角色id") @PathVariable Long roleId) {
        logger.info("roleId={}", roleId);
        List<MenuVo> menuVoList = menuService.treeMenuByRoleIds(Lists.newArrayList(roleId));
        return WrapMapper.ok(menuVoList);
    }

    /**
     * 角色绑定用户.
     *
     * @param roleBindUserReqDto the role bind user req dto
     *
     * @return the wrapper
     */
    @LogAnnotation
    @PostMapping(value = "/bindUser")
    @ApiOperation(httpMethod = "POST", value = "角色绑定用户")
    public Wrapper bindUser(@Validated @RequestBody RoleBindUserReqDto roleBindUserReqDto) {
        logger.info("roleBindUser={}", roleBindUserReqDto);
        UserTokenDto loginAuthDto = getLoginAuthDto();
        roleService.bindUser4Role(roleBindUserReqDto, loginAuthDto);
        return WrapMapper.ok();
    }

    /**
     * 获取角色绑定用户页面数据.
     *
     * @param roleId the role id
     *
     * @return the wrapper
     */
    @PostMapping(value = "/getBindUser/{roleId}")
    @ApiOperation(httpMethod = "POST", value = "获取角色绑定用户页面数据")
    public Wrapper<RoleBindUserDto> getBindUser(@ApiParam(name = "roleId", value = "角色id") @PathVariable Long roleId) {
        logger.info("获取角色绑定用户页面数据. roleId={}", roleId);
        UserTokenDto loginAuthDto = super.getLoginAuthDto();

        RoleBindUserDto bindUserDto = roleService.getRoleBindUserDto(roleId, loginAuthDto);
        return WrapMapper.ok(bindUserDto);
    }

}
