package com.maxmall.common.core.utils;

import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.base.exception.BusinessException;
import org.springframework.stereotype.Component;

@Component
public class PermissionCheckUtil {

    /**
     * 验证商户权限
     *
     * @param merchantId
     * @param loginAuthDto
     */
    public static void checkDataMerchantPermission(Long merchantId, UserTokenDto loginAuthDto) {
        if (merchantId == null){
            throw new BusinessException(ErrorCodeEnum.GL99990402);
        }

        if (!loginAuthDto.getMerchantId().equals(merchantId)){
            throw new BusinessException(ErrorCodeEnum.GL99990401);
        }

    }

}
