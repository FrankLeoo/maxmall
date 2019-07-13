package com.maxmall.provider.merchant.web.portal;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.BaseController;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.merchant.model.dto.merchant.DisCommissionModifyDto;
import com.maxmall.provider.merchant.model.dto.merchant.DisCommissionQueryDto;
import com.maxmall.provider.merchant.model.vo.DisCommissionConfigVo;
import com.maxmall.provider.merchant.service.DisCommissionConfigService;
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
@RequestMapping(value = GlobalConstant.PORTAL_PATH + "/distributor/config/commission", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Web - DistributorLevelConfigController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DistributorCommissionConfigController extends BaseController {

    @Autowired
    private DisCommissionConfigService commissionConfigService;

    @ApiOperation(value = "获取全部销售额返点配置列表", httpMethod = "POST")
    @PostMapping(value = "/listAll")
    public Wrapper<List<DisCommissionConfigVo>> getList() {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        return WrapMapper.ok(commissionConfigService.listAllCommission(loginAuthDto));
    }

    @ApiOperation(value = "分页获取销售额返点配置列表", httpMethod = "POST")
    @PostMapping(value = "/searchByPage")
    public Wrapper<PageInfo<DisCommissionConfigVo>> searchByPage(@RequestBody DisCommissionQueryDto queryParam) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        PageInfo<DisCommissionConfigVo> pageResult = commissionConfigService.queryCommissionListWithPage(queryParam,loginAuthDto);
        return WrapMapper.ok(pageResult);
    }

    @ApiOperation(value = "添加或修改销售额返点配置", httpMethod = "POST")
    @PostMapping(value = "/modify")
    public Wrapper<Integer> modifyBrand(@Validated @RequestBody DisCommissionModifyDto brandDto) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int count = commissionConfigService.saveOrModifyCommission(brandDto,loginAuthDto);
        if(count>0){
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }

    @ApiOperation(value = "删除销售额返点配置", httpMethod = "GET")
    @GetMapping(value = "/delete/{id}")
    public Wrapper delete(@PathVariable("id") Long id) {
        UserTokenDto loginAuthDto = getLoginAuthDto();
        int count = commissionConfigService.deleteCommission(id,loginAuthDto);

        if (count == 1) {
            return WrapMapper.ok(null);
        } else {
            return WrapMapper.error();
        }
    }

    @ApiOperation(value = "根据编号查询销售额返点配置信息", httpMethod = "GET")
    @GetMapping(value = "/detail/{id}")
    public Wrapper<DisCommissionConfigVo> brandDetail(@PathVariable("id") Long id) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        DisCommissionConfigVo result = commissionConfigService.commissionDetail(id,loginAuthDto);
        return WrapMapper.ok(result);
    }

}
