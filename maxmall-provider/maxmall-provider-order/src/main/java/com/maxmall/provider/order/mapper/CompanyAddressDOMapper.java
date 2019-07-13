package com.maxmall.provider.order.mapper;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.order.model.domain.CompanyAddressDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table OMS_COMPANY_ADDRESS.
 * 公司收发货地址表
 */
@Mapper
@Component
public interface CompanyAddressDOMapper extends MyMapper<CompanyAddressDO> {


}
