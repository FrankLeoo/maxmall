
package com.maxmall.provider.product.service.hystrix;

import com.google.common.collect.Lists;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.product.model.dto.ProductDto;
import com.maxmall.provider.product.model.dto.ProductReqDto;
import com.maxmall.provider.product.service.PmsProductFeignApi;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * The class Mdc product feign hystrix.
 *
 * @author maxmall.net@gmail.com
 */
@Component
public class PmsProductFeignHystrix implements PmsProductFeignApi {

    /**
     * 描述：限流返沪空数据
     *
     * @author ivoter
     * @date 2019/5/5 2:55 PM
     * @param: ids
     * @return: com.maxmall.common.util.wrapper.Wrapper<java.util.List<com.maxmall.provider.product.model.dto.ProductDto>>
     * @throws
     */
    @Override
    public Wrapper<List<ProductDto>> selectByIds(@RequestBody ProductReqDto productReqDto) {
        return WrapMapper.ok(Lists.newArrayList());
    }
}
