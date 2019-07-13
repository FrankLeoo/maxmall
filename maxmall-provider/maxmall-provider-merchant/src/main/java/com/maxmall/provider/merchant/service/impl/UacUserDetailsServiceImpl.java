package com.maxmall.provider.merchant.service.impl;

import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.security.SecurityUser;
import com.maxmall.provider.merchant.model.domain.account.AccountDO;
import com.maxmall.provider.merchant.model.domain.merchant.MerchantDO;
import com.maxmall.provider.merchant.service.AccountService;
import com.maxmall.provider.merchant.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * The class Uac user details service.
 *
 * @author maxmall.net@gmail.com
 */
@Component
public class UacUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private AccountService accountService;
    @Autowired
    private MerchantService merchantService;

    /**
     * Load user by username user details.
     *
     * @param username the username
     * @return the user details
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        Collection<GrantedAuthority> grantedAuthorities;
        AccountDO user = accountService.findByLoginName(username);
        if (user == null) {
            throw new BadCredentialsException("用户名不存在或者密码错误");
        }
        Long merchantId = user.getMerchantId();
        //查询商户
        if (GlobalConstant.CommonStatusEnum.AUDIT.getStatus()==user.getStatus() || merchantId==null){
            throw new BadCredentialsException("商户审核中，请耐心等待");
        }
        if(GlobalConstant.CommonStatusEnum.ENABLE.getStatus() != user.getStatus()){
            throw new BadCredentialsException("禁用用户，请联系商户管理员");
        }
        MerchantDO merchantDO = merchantService.selectByKey(merchantId);
        if (merchantDO == null || GlobalConstant.CommonStatusEnum.ENABLE.getStatus()!= merchantDO.getStatus()){
            throw new BadCredentialsException("禁用商户，请联系管理员");
        }
        grantedAuthorities = accountService.loadUserAuthorities(user.getId());

        SecurityUser securityUser = new SecurityUser(user.getId(), user.getLoginName(), user.getLoginPwd(),user.getUserName(), user.getStatus(), grantedAuthorities);
        securityUser.setIsMaster(user.getIsMaster());
        securityUser.setMerchantId(merchantDO.getId());
        securityUser.setMerchantName(merchantDO.getName());
        securityUser.setMerchantSn(merchantDO.getMerchantSn());
        securityUser.setMerchantPic(merchantDO.getPic());

        return securityUser;
    }
}
