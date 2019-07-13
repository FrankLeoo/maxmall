package com.maxmall.provider.merchant.service.impl;

import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.core.support.BaseService;
import com.maxmall.common.util.converter.BeanConverter;
import com.maxmall.provider.merchant.exception.UacBizException;
import com.maxmall.provider.merchant.mapper.account.RoleDOMapper;
import com.maxmall.provider.merchant.mapper.merchant.MerchantDOMapper;
import com.maxmall.provider.merchant.model.domain.account.RoleDO;
import com.maxmall.provider.merchant.model.domain.merchant.MerchantDO;
import com.maxmall.provider.merchant.model.vo.MerchantConfigVo;
import com.maxmall.provider.merchant.model.vo.MerchantVo;
import com.maxmall.provider.merchant.service.MerchantConfigService;
import com.maxmall.provider.merchant.service.MerchantService;
import com.maxmall.provider.merchant.service.RoleService;
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
public class MerchantServiceImpl extends BaseService<MerchantDO> implements MerchantService {

    @Autowired
    private MerchantDOMapper merchantDOMapper;
    @Autowired
    private MerchantConfigService merchantConfigService;

    /**
     * 根据ID查询商户详情
     * @param merchantId
     * @param loginAuthDto
     * @return
     */
    @Override
    public MerchantVo getMerchantById(Long merchantId, UserTokenDto loginAuthDto) {
        MerchantDO merchantDO =  merchantDOMapper.selectByPrimaryKey(merchantId);
        if (merchantDO == null){
            throw new UacBizException(ErrorCodeEnum.GL99990405,merchantId);
        }
        PermissionUtil.checkDataMerchantPermission(merchantId,loginAuthDto);
        MerchantVo result = BeanConverter.convert(merchantDO,MerchantVo.class);
        //获取商户配置
        MerchantConfigVo configVo = merchantConfigService.getConfigByMerchantId(merchantId,loginAuthDto);
        result.setMerchantConfig(configVo);

        return result;
    }
}
