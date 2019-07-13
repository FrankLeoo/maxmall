package com.maxmall.provider.merchant.mapper.merchant;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.merchant.model.domain.merchant.MerchantDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table MCS_MERCHANT.
 * 商户主表
 */
@Mapper
@Component
public interface MerchantDOMapper extends MyMapper<MerchantDO> {


}
