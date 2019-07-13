package com.maxmall.provider.merchant.web.portal;

import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.core.support.BaseController;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.merchant.model.vo.MenuVo;
import com.maxmall.provider.merchant.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单主页面.
 *
 * @author maxmall.net@gmail.com
 */
@RestController
@RequestMapping(value = GlobalConstant.PORTAL_PATH + "/menu", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Web - MenuMainController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MenuMainController extends BaseController {

    @Resource
    private MenuService menuService;

    /**
     * 获取菜单列表数据
     *
     * @return the wrapper
     */
    @PostMapping(value = "/getTree")
    @ApiOperation(httpMethod = "POST", value = "获取菜单树")
    public Wrapper<List<MenuVo>> queryMenuTreeList() {
        List<MenuVo> menuVoList = menuService.getMenuVoList(getLoginAuthDto().getUserId());
        return WrapMapper.ok(menuVoList);
    }

    /**
     * 获取菜单详情.
     *
     * @param menuId the id
     * @return the wrapper
     */
    @PostMapping(value = "/queryById/{menuId}")
    @ApiOperation(httpMethod = "POST", value = "编辑菜单")
    public Wrapper<MenuVo> queryMenuVoById(@ApiParam(name = "menuId", value = "菜单id") @PathVariable Long menuId) {
        MenuVo menuVo = menuService.getMenuVoById(menuId);
        return WrapMapper.ok(menuVo);
    }


}