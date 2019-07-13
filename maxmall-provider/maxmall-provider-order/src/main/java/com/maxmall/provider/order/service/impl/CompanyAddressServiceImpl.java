package com.maxmall.provider.order.service.impl;

import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.BaseService;
import com.maxmall.common.util.converter.BeanConverter;
import com.maxmall.provider.order.mapper.CompanyAddressDOMapper;
import com.maxmall.provider.order.model.domain.CompanyAddressDO;
import com.maxmall.provider.order.model.vo.CompanyAddressVo;
import com.maxmall.provider.order.service.CompanyAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ivoter
 * @ClassName OrderItemServiceImpl.java
 * @date 2019/05/22 10:26:00
 * @Description 公司地址管理service
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class CompanyAddressServiceImpl extends BaseService<CompanyAddressDO> implements CompanyAddressService {

    @Autowired
    private CompanyAddressDOMapper companyAddressDOMapper;

    /**
     * 获取公司收货地址列表
     * @param loginAuthDto
     * @return
     */
    @Override
    public List<CompanyAddressVo> listAllAddress(UserTokenDto loginAuthDto) {
        CompanyAddressDO param = new CompanyAddressDO();
        param.setMerchantId(loginAuthDto.getMerchantId());

        List<CompanyAddressDO> addressDOList = companyAddressDOMapper.select(param);

        return BeanConverter.batchConvert(addressDOList,CompanyAddressVo.class);
    }
}
