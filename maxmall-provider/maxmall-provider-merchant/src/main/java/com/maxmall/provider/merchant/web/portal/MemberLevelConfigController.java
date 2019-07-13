package com.maxmall.provider.merchant.web.portal;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.BaseController;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.merchant.model.dto.merchant.MemberLevelModifyDto;
import com.maxmall.provider.merchant.model.dto.merchant.MemberLevelQueryDto;
import com.maxmall.provider.merchant.model.vo.MemberLevelConfigVo;
import com.maxmall.provider.merchant.service.MemberLevelConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 菜单主页面.
 *
 * @author maxmall.net@gmail.com
 */
@RestController
@RequestMapping(value = GlobalConstant.PORTAL_PATH + "/config/level", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Web - MemberLevelConfigController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MemberLevelConfigController extends BaseController {

    @Autowired
    private MemberLevelConfigService memberLevelConfigService;

    @ApiOperation(value = "获取全部等级配置列表", httpMethod = "POST")
    @PostMapping(value = "/listAll")
    public Wrapper<List<MemberLevelConfigVo>> getList() {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        return WrapMapper.ok(memberLevelConfigService.listAllLevel(loginAuthDto));
    }

    @ApiOperation(value = "分页获取等级配置列表", httpMethod = "POST")
    @PostMapping(value = "/searchByPage")
    public Wrapper<PageInfo<MemberLevelConfigVo>> searchByPage(@Validated @RequestBody MemberLevelQueryDto queryParam) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        PageInfo<MemberLevelConfigVo> pageResult = memberLevelConfigService.queryLevelListWithPage(queryParam,loginAuthDto);
        return WrapMapper.ok(pageResult);
    }

    @ApiOperation(value = "添加或修改等级配置", httpMethod = "POST")
    @PostMapping(value = "/modify")
    public Wrapper<Integer> modifyBrand(@Validated @RequestBody MemberLevelModifyDto brandDto) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int count = memberLevelConfigService.saveOrModifyLevel(brandDto,loginAuthDto);
        if(count>0){
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }

    @ApiOperation(value = "删除等级配置", httpMethod = "GET")
    @GetMapping(value = "/delete/{id}")
    public Wrapper delete(@PathVariable("id") Long id) {
        UserTokenDto loginAuthDto = getLoginAuthDto();
        int count = memberLevelConfigService.deleteLevel(id,loginAuthDto);

        if (count == 1) {
            return WrapMapper.ok(null);
        } else {
            return WrapMapper.error();
        }
    }

    @ApiOperation(value = "根据编号查询等级配置信息", httpMethod = "GET")
    @GetMapping(value = "/detail/{id}")
    public Wrapper<MemberLevelConfigVo> brandDetail(@PathVariable("id") Long id) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        MemberLevelConfigVo result = memberLevelConfigService.levelDetail(id,loginAuthDto);
        return WrapMapper.ok(result);
    }

}
