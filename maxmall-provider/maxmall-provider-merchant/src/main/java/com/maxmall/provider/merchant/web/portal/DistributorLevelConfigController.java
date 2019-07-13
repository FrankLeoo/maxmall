package com.maxmall.provider.merchant.web.portal;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.BaseController;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.merchant.model.dto.merchant.DisLevelModifyDto;
import com.maxmall.provider.merchant.model.dto.merchant.DisLevelQueryDto;
import com.maxmall.provider.merchant.model.vo.DistributorLevelConfigVo;
import com.maxmall.provider.merchant.service.DisLevelConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 菜单主页面.
 *
 * @author maxmall.net@gmail.com
 */
@RestController
@RequestMapping(value = GlobalConstant.PORTAL_PATH + "/distributor/config/level", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Web - DistributorLevelConfigController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DistributorLevelConfigController extends BaseController {

    @Autowired
    private DisLevelConfigService distributorLevelConfigService;

    @ApiOperation(value = "获取全部等级配置列表", httpMethod = "POST")
    @PostMapping(value = "/listAll")
    public Wrapper<List<DistributorLevelConfigVo>> getList() {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        return WrapMapper.ok(distributorLevelConfigService.listAllLevel(loginAuthDto));
    }

    @ApiOperation(value = "分页获取等级配置列表", httpMethod = "POST")
    @PostMapping(value = "/searchByPage")
    public Wrapper<PageInfo<DistributorLevelConfigVo>> searchByPage(@Validated @RequestBody DisLevelQueryDto queryParam) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        PageInfo<DistributorLevelConfigVo> pageResult = distributorLevelConfigService.queryLevelListWithPage(queryParam,loginAuthDto);
        return WrapMapper.ok(pageResult);
    }

    @ApiOperation(value = "添加或修改等级配置", httpMethod = "POST")
    @PostMapping(value = "/modify")
    public Wrapper<Integer> modifyBrand(@Validated @RequestBody DisLevelModifyDto brandDto) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int count = distributorLevelConfigService.saveOrModifyLevel(brandDto,loginAuthDto);
        if(count>0){
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }

    @ApiOperation(value = "删除等级配置", httpMethod = "GET")
    @GetMapping(value = "/delete/{id}")
    public Wrapper delete(@PathVariable("id") Long id) {
        UserTokenDto loginAuthDto = getLoginAuthDto();
        int count = distributorLevelConfigService.deleteLevel(id,loginAuthDto);

        if (count == 1) {
            return WrapMapper.ok(null);
        } else {
            return WrapMapper.error();
        }
    }

    @ApiOperation(value = "根据编号查询等级配置信息", httpMethod = "GET")
    @GetMapping(value = "/detail/{id}")
    public Wrapper<DistributorLevelConfigVo> brandDetail(@PathVariable("id") Long id) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        DistributorLevelConfigVo result = distributorLevelConfigService.levelDetail(id,loginAuthDto);
        return WrapMapper.ok(result);
    }

}
