package com.maxmall.provider.product.web.portal;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.BaseController;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.product.model.dto.ProductLabelModifyDto;
import com.maxmall.provider.product.model.dto.ProductListQueryDto;
import com.maxmall.provider.product.model.dto.ProductModifyDto;
import com.maxmall.provider.product.model.dto.ProductQueryDto;
import com.maxmall.provider.product.model.vo.ProductVo;
import com.maxmall.provider.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 商品Controller
 * Created by maxmall on 2018/4/26.
 */
@RestController
@RequestMapping(value = GlobalConstant.PORTAL_PATH + "/product")
@Api(value = "WEB - ProductController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    /**
     * 创建商品时spu和sku一起创建
     */
    @ApiOperation(value = "添加或修改商品", httpMethod = "POST")
    @PostMapping(value = "/modify")
    public Wrapper<Integer> modifyProduct(@Validated @RequestBody ProductModifyDto modifyDto) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int count = productService.saveOrModifyProduct(modifyDto,loginAuthDto);
        if(count>0){
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }

    @ApiOperation(value = "分页获取商品列表", httpMethod = "POST")
    @PostMapping(value = "/searchByPage")
    public Wrapper<PageInfo<ProductVo>> searchByPage(@Validated @RequestBody ProductQueryDto queryParam) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        PageInfo<ProductVo> pageResult = productService.queryProductListWithPage(queryParam,loginAuthDto);
        return WrapMapper.ok(pageResult);
    }

    @ApiOperation(value = "查询商品详情", httpMethod = "GET")
    @GetMapping(value = "/detail/{id}")
    public Wrapper<ProductVo> productDetail(@PathVariable("id") Long id) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        ProductVo result = productService.productDetail(id,loginAuthDto);
        return WrapMapper.ok(result);
    }

    @ApiOperation("批量删除商品")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public Wrapper delete(@PathVariable Long id) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int count = productService.deleteByIds(Lists.newArrayList(id),loginAuthDto);
        if (count > 0) {
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }

    /**
     * 修改商品标签
     */
    @ApiOperation(value = "修改商品Label", httpMethod = "POST")
    @PostMapping(value = "/modify/label")
    public Wrapper<Integer> modifyLabel(@Validated @RequestBody ProductLabelModifyDto modifyDto) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int count = productService.modifyProductLabel(modifyDto,loginAuthDto);
        if(count>0){
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }

    /**
     * 优惠券列表
     * @return
     */
    @PostMapping(value = "/listAll")
    @ApiOperation(httpMethod = "POST", value = "全部优惠券列表")
    public Wrapper<List<ProductVo>> queryAllList(@Validated @RequestBody ProductListQueryDto queryParam) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        List<ProductVo> result = productService.productListAll(loginAuthDto,queryParam);
        return WrapMapper.ok(result);
    }

}
