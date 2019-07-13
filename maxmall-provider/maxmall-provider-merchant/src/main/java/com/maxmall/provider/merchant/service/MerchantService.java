package com.maxmall.provider.merchant.service;

import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.IService;
import com.maxmall.provider.merchant.model.domain.account.RoleDO;
import com.maxmall.provider.merchant.model.domain.merchant.MerchantDO;
import com.maxmall.provider.merchant.model.vo.MerchantVo;

/**
 * @author ivoter
 * @ClassName MenuService.java
 * @date 2019/05/22 10:25:00
 * @Description 商户service
 */
public interface MerchantService extends IService<MerchantDO> {

    /**
     * 根据id查询商户详情
     * @param merchantId
     * @param loginAuthDto
     * @return
     */
    MerchantVo getMerchantById(Long merchantId, UserTokenDto loginAuthDto);
}
