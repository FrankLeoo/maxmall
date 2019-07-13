package com.maxmall.provider.merchant.service;

import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.IService;
import com.maxmall.provider.merchant.model.domain.merchant.MerchantConfigDO;
import com.maxmall.provider.merchant.model.dto.merchant.MerchantConfigDto;
import com.maxmall.provider.merchant.model.dto.merchant.MerchantPayDto;
import com.maxmall.provider.merchant.model.dto.merchant.MerchantTimeDto;
import com.maxmall.provider.merchant.model.vo.MerchantConfigVo;

public interface MerchantConfigService extends IService<MerchantConfigDO> {

    /**
     * 查询商户配置信息
     * @param merchantId
     * @param loginAuthDto
     * @return
     */
    MerchantConfigVo getConfigByMerchantId(Long merchantId, UserTokenDto loginAuthDto);

    /**
     * 更新支付配置
     * @param merchantPayDto
     * @param loginAuthDto
     * @return
     */
    int modifyPayconfig(MerchantPayDto merchantPayDto, UserTokenDto loginAuthDto);

    /**
     * 更新订单时间配置
     * @param merchantTimeDto
     * @param loginAuthDto
     * @return
     */
    int modifyTimeconfig(MerchantTimeDto merchantTimeDto, UserTokenDto loginAuthDto);

    /**
     * 更新基础配置
     * @param merchantConfigDto
     * @param loginAuthDto
     * @return
     */
    int modifyConfig(MerchantConfigDto merchantConfigDto, UserTokenDto loginAuthDto);
}
