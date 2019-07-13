package com.maxmall.provider.merchant.mapper.merchant;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.merchant.model.domain.merchant.MerchantConfigDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table MCS_MERCHANT_CONFIG.
 * 商户配置表
 */
@Mapper
@Component
public interface MerchantConfigDOMapper extends MyMapper<MerchantConfigDO> {


}
