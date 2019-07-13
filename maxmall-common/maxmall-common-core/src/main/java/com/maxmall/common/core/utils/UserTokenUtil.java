package com.maxmall.common.core.utils;

import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.base.exception.BusinessException;
import com.maxmall.common.util.PublicUtil;
import com.maxmall.common.util.ThreadLocalMap;

public class UserTokenUtil {

    /**
     * Gets login user.
     *
     * @return the login user
     */
    public static UserTokenDto getLoginUser() {
        UserTokenDto loginAuthDto = (UserTokenDto) ThreadLocalMap.get(GlobalConstant.Sys.TOKEN_AUTH_DTO);
        if (PublicUtil.isEmpty(loginAuthDto)) {
            throw new BusinessException(ErrorCodeEnum.UAC10011039);
        }
        return loginAuthDto;

    }

    /**
     * 设置用户token
     * @param loginUser
     */
    public static void setLoginUser(UserTokenDto loginUser) {
        ThreadLocalMap.put(GlobalConstant.Sys.TOKEN_AUTH_DTO, loginUser);
    }
}
