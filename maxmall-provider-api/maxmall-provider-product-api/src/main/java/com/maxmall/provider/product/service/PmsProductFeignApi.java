package com.maxmall.provider.product.service;

import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.product.model.dto.ProductDto;
import com.maxmall.provider.product.model.dto.ProductReqDto;
import com.maxmall.provider.product.service.hystrix.PmsProductFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * The interface Mdc product feign api.
 *
 * @author maxmall.net @gmail.com
 */
@FeignClient(value = "maxmall-provider-product", fallback = PmsProductFeignHystrix.class)
public interface PmsProductFeignApi {

	/**
	 * 根据param查询商品列表
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/api/product/selectByIds")
	Wrapper<List<ProductDto>> selectByIds(@RequestBody ProductReqDto productReqDto);
}
