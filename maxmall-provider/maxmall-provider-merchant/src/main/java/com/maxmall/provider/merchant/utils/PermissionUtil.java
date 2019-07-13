package com.maxmall.provider.merchant.utils;

import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.provider.merchant.exception.UacBizException;
import com.maxmall.provider.merchant.mapper.account.AccountDOMapper;
import com.maxmall.provider.merchant.model.domain.account.AccountDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PermissionUtil {

    @Autowired
    private static AccountDOMapper accountDOMapper;

    /**
     * 验证商户权限
     *
     * @param merchantId
     * @param loginAuthDto
     */
    public static void checkDataMerchantPermission(Long merchantId, UserTokenDto loginAuthDto) {
        if (merchantId == null){
            throw new UacBizException(ErrorCodeEnum.GL99990402);
        }

        if (!loginAuthDto.getMerchantId().equals(merchantId)){
            throw new UacBizException(ErrorCodeEnum.GL99990401);
        }

    }

    @Autowired
    public void setAccountDOMapper(AccountDOMapper accountDOMapper) {
        PermissionUtil.accountDOMapper = accountDOMapper;
    }

    /**
     * 权限验证
     * @param userId
     * @param userTokenDto
     */
    public static AccountDO checkDataPermission(Long userId, UserTokenDto userTokenDto){
        AccountDO accountDO = accountDOMapper.selectByPrimaryKey(userId);
        if (accountDO == null) {
            throw new UacBizException(ErrorCodeEnum.UAC10011011, userId);
        }
        if (userTokenDto.getMerchantId() == null){
            throw new UacBizException(ErrorCodeEnum.GL99990402);
        }

        if (!userTokenDto.getMerchantId().equals(accountDO.getMerchantId())){
            throw new UacBizException(ErrorCodeEnum.GL99990401);
        }
        return accountDO;
    }

}
