package com.maxmall.provider.order.service;

import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.IService;
import com.maxmall.provider.order.model.domain.CompanyAddressDO;
import com.maxmall.provider.order.model.vo.CompanyAddressVo;

import java.util.List;

/**
 * @author ivoter
 * @ClassName CompanyAddressService.java
 * @date 2019/05/21 17:18:00
 * @Description 订单service
 */
public interface CompanyAddressService extends IService<CompanyAddressDO> {

    /**
     * 获取公司收货地址列表
     * @param loginAuthDto
     * @return
     */
    List<CompanyAddressVo> listAllAddress(UserTokenDto loginAuthDto);
}
