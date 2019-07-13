package com.maxmall.provider.product.web.portal;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.BaseController;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.product.model.dto.CategoryModifyDto;
import com.maxmall.provider.product.model.dto.CategoryQueryDto;
import com.maxmall.provider.product.model.dto.CategoryShowStatusDto;
import com.maxmall.provider.product.model.vo.CategoryVo;
import com.maxmall.provider.product.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品分类功能Controller
 * Created by maxmall on 2018/4/26.
 */
@RestController
@RequestMapping(value = GlobalConstant.PORTAL_PATH + "/category")
@Api(value = "WEB - CategoryController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取全部商品分类列表", httpMethod = "POST")
    @PostMapping(value = "/listAll")
    public Wrapper<List<CategoryVo>> getList() {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        return WrapMapper.ok(categoryService.listAllCategory(loginAuthDto));
    }

    @ApiOperation(value = "获取全部商品分类列表", httpMethod = "POST")
    @PostMapping(value = "/treeList")
    public Wrapper<List<CategoryVo>> getTreeList(@Validated @RequestBody CategoryQueryDto queryParam) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        return WrapMapper.ok(categoryService.getTreeCategory(loginAuthDto,queryParam));
    }

    @ApiOperation(value = "分页获取商品分类列表", httpMethod = "POST")
    @PostMapping(value = "/searchByPage")
    public Wrapper<PageInfo<CategoryVo>> searchByPage(@Validated @RequestBody CategoryQueryDto queryParam) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        PageInfo<CategoryVo> pageResult = categoryService.queryCategoryListWithPage(queryParam,loginAuthDto);
        return WrapMapper.ok(pageResult);
    }

    @ApiOperation(value = "添加或修改商品分类", httpMethod = "POST")
    @PostMapping(value = "/modify")
    public Wrapper<Integer> modifyCategory(@Validated @RequestBody CategoryModifyDto modifyDto) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int count = categoryService.saveOrModifyCategory(modifyDto,loginAuthDto);
        if(count>0){
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }

    @ApiOperation(value = "删除商品分类", httpMethod = "GET")
    @GetMapping(value = "/delete/{id}")
    public Wrapper<Integer> delete(@PathVariable("id") Long id) {
        UserTokenDto loginAuthDto = getLoginAuthDto();
        int count = categoryService.deleteCategory(id,loginAuthDto);

        if (count == 1) {
            return WrapMapper.ok(null);
        } else {
            return WrapMapper.error();
        }
    }

    @ApiOperation(value = "根据编号查询商品分类信息", httpMethod = "GET")
    @GetMapping(value = "/detail/{id}")
    public Wrapper<CategoryVo> categoryDetail(@PathVariable("id") Long id) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        CategoryVo result = categoryService.categoryDetail(id,loginAuthDto);
        return WrapMapper.ok(result);
    }

    @ApiOperation(value = "更新商品分类显示状态", httpMethod = "POST")
    @PostMapping(value = "/update/showStatus")
    public Wrapper<Integer> updateShowStatus(@Validated @RequestBody CategoryShowStatusDto statusDto) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int status = categoryService.updateShowStatus(statusDto.getIds(), statusDto.getShowStatus(), loginAuthDto);
        return WrapMapper.ok(status);
    }

}
