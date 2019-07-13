
package com.maxmall.provider.product.web.fegin;

import com.maxmall.common.core.support.BaseController;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.product.model.dto.ProductDto;
import com.maxmall.provider.product.model.dto.ProductReqDto;
import com.maxmall.provider.product.service.PmsProductFeignApi;
import com.maxmall.provider.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * 商品信息查询.
 *
 * @author maxmall.net @gmail.com
 */
@RestController
@Api(value = "API - PmsProductFeignApiClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PmsProductFeignApiClient extends BaseController implements PmsProductFeignApi {
	@Resource
	private ProductService productService;

    @Override
    @ApiOperation(httpMethod = "POST", value = "根据id列表查询商品")
    public Wrapper<List<ProductDto>> selectByIds(@RequestBody ProductReqDto productReqDto) {
        return productService.selectByIds(productReqDto.getIds(),productReqDto.getMerchantId());
    }
}
