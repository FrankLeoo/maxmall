package com.maxmall.provider.merchant.service.impl;

import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.core.support.BaseService;
import com.maxmall.common.util.converter.BeanConverter;
import com.maxmall.provider.merchant.exception.UacBizException;
import com.maxmall.provider.merchant.mapper.merchant.MerchantConfigDOMapper;
import com.maxmall.provider.merchant.model.domain.merchant.MerchantConfigDO;
import com.maxmall.provider.merchant.model.dto.merchant.MerchantConfigDto;
import com.maxmall.provider.merchant.model.dto.merchant.MerchantPayDto;
import com.maxmall.provider.merchant.model.dto.merchant.MerchantTimeDto;
import com.maxmall.provider.merchant.model.vo.MerchantConfigVo;
import com.maxmall.provider.merchant.service.MerchantConfigService;
import com.maxmall.provider.merchant.utils.PermissionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ivoter
 * @ClassName MenuServiceImpl.java
 * @date 2019/05/22 10:26:00
 * @Description 角色实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MerchantConfigServiceImpl extends BaseService<MerchantConfigDO> implements MerchantConfigService {

    @Autowired
    private MerchantConfigDOMapper merchantConfigDOMapper;

    /**
     * 查询商户配置信息
     * @param merchantId
     * @param loginAuthDto
     * @return
     */
    @Override
    public MerchantConfigVo getConfigByMerchantId(Long merchantId, UserTokenDto loginAuthDto) {
        MerchantConfigDO configDO = queryConfigByMerchantId(merchantId,loginAuthDto);

        MerchantConfigVo result = BeanConverter.convert(configDO,MerchantConfigVo.class);
        return result;
    }

    /**
     * 更新支付配置
     * @param merchantPayDto
     * @param loginAuthDto
     * @return
     */
    @Override
    public int modifyPayconfig(MerchantPayDto merchantPayDto, UserTokenDto loginAuthDto) {
        MerchantConfigDO configDO = queryConfigByMerchantId(merchantPayDto.getMerchantId(),loginAuthDto);

        MerchantConfigDO update = new MerchantConfigDO();
        update.setId(configDO.getId());
        update.setPayType(merchantPayDto.getPayType());
        update.setPayAccount(merchantPayDto.getPayAccount());

        update.setUpdateInfo(loginAuthDto);
        return merchantConfigDOMapper.updateByPrimaryKeySelective(update);
    }

    private MerchantConfigDO queryConfigByMerchantId(Long merchantId,UserTokenDto loginAuthDto){
        PermissionUtil.checkDataMerchantPermission(merchantId,loginAuthDto);

        MerchantConfigDO param = new MerchantConfigDO();
        param.setMerchantId(merchantId);
        MerchantConfigDO configDO = merchantConfigDOMapper.selectOne(param);

        if (configDO == null){
            throw new UacBizException(ErrorCodeEnum.GL99990405,merchantId);
        }

        return configDO;
    }

    /**
     * 更新商户订单时间配置
     * @param merchantTimeDto
     * @param loginAuthDto
     * @return
     */
    @Override
    public int modifyTimeconfig(MerchantTimeDto merchantTimeDto, UserTokenDto loginAuthDto) {
        MerchantConfigDO configDO = queryConfigByMerchantId(loginAuthDto.getMerchantId(),loginAuthDto);

        MerchantConfigDO update = new MerchantConfigDO();
        update.setId(configDO.getId());
        update.setFinishOvertime(merchantTimeDto.getFinishOvertime());
        update.setCommentOvertime(merchantTimeDto.getCommentOvertime());
        update.setConfirmOvertime(merchantTimeDto.getConfirmOvertime());
        update.setFlashOrderOvertime(merchantTimeDto.getFlashOrderOvertime());
        update.setNormalOrderOvertime(merchantTimeDto.getNormalOrderOvertime());

        update.setUpdateInfo(loginAuthDto);

        return merchantConfigDOMapper.updateByPrimaryKeySelective(update);
    }

    /**
     * 更新商户基础配置
     * @param merchantConfigDto
     * @param loginAuthDto
     * @return
     */
    @Override
    public int modifyConfig(MerchantConfigDto merchantConfigDto, UserTokenDto loginAuthDto) {
        MerchantConfigDO configDO = queryConfigByMerchantId(loginAuthDto.getMerchantId(),loginAuthDto);

        MerchantConfigDO update = new MerchantConfigDO();
        update.setId(configDO.getId());
        update.setIsPoint(merchantConfigDto.getIsPoint());
        update.setIsGrowth(merchantConfigDto.getIsGrowth());
        update.setIsDistributor(merchantConfigDto.getIsDistributor());
        update.setIsCommission(merchantConfigDto.getIsCommission());
        update.setPointCharge(merchantConfigDto.getPointCharge());
        update.setGrowthCharge(merchantConfigDto.getGrowthCharge());
        update.setUsePointLimit(merchantConfigDto.getUsePointLimit());

        update.setUpdateInfo(loginAuthDto);

        return merchantConfigDOMapper.updateByPrimaryKeySelective(update);
    }
}
