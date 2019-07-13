package com.maxmall.provider.merchant.mapper.account;

import com.maxmall.common.core.mybatis.MyMapper;
import com.maxmall.provider.merchant.model.domain.account.RoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * The Table SSC_ROLE.
 * 商户角色
 */
@Mapper
@Component
public interface RoleDOMapper extends MyMapper<RoleDO> {


}
