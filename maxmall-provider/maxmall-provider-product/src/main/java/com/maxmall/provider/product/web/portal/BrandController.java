package com.maxmall.provider.product.web.portal;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.BaseController;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.product.model.dto.BrandModifyDto;
import com.maxmall.provider.product.model.dto.BrandQueryDto;
import com.maxmall.provider.product.model.vo.BrandVo;
import com.maxmall.provider.product.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌功能Controller
 * Created by maxmall on 2018/4/26.
 */
@RestController
@RequestMapping(value = GlobalConstant.PORTAL_PATH + "/brand")
@Api(value = "WEB - BrandController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BrandController extends BaseController {

    @Autowired
    private BrandService brandService;

    @ApiOperation(value = "获取全部品牌列表", httpMethod = "POST")
    @PostMapping(value = "/listAll")
    public Wrapper<List<BrandVo>> getList() {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        return WrapMapper.ok(brandService.listAllBrand(loginAuthDto));
    }

    @ApiOperation(value = "分页获取品牌列表", httpMethod = "POST")
    @PostMapping(value = "/searchByPage")
    public Wrapper<PageInfo<BrandVo>> searchByPage(@Validated @RequestBody BrandQueryDto queryParam) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        PageInfo<BrandVo> pageResult = brandService.queryBrandListWithPage(queryParam,loginAuthDto);
        return WrapMapper.ok(pageResult);
    }

    @ApiOperation(value = "添加或修改品牌", httpMethod = "POST")
    @PostMapping(value = "/modify")
    public Wrapper<Integer> modifyBrand(@Validated @RequestBody BrandModifyDto brandDto) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int count = brandService.saveOrModifyBrand(brandDto,loginAuthDto);
        if(count>0){
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }

    @ApiOperation(value = "删除品牌", httpMethod = "GET")
    @GetMapping(value = "/delete/{id}")
    public Wrapper delete(@PathVariable("id") Long id) {
        UserTokenDto loginAuthDto = getLoginAuthDto();
        int count = brandService.deleteBrand(id,loginAuthDto);

        if (count == 1) {
            return WrapMapper.ok(null);
        } else {
            return WrapMapper.error();
        }
    }

    @ApiOperation(value = "根据编号查询品牌信息", httpMethod = "GET")
    @GetMapping(value = "/detail/{id}")
    public Wrapper<BrandVo> brandDetail(@PathVariable("id") Long id) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        BrandVo result = brandService.brandDetail(id,loginAuthDto);
        return WrapMapper.ok(result);
    }

}
