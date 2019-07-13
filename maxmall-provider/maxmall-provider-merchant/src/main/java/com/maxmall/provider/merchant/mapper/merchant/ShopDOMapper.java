package com.maxmall.provider.merchant.mapper.merchant;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.merchant.model.domain.merchant.ShopDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table MCS_SHOP.
 * 店铺表
 */
@Mapper
@Component
public interface ShopDOMapper extends MyMapper<ShopDO> {


}
