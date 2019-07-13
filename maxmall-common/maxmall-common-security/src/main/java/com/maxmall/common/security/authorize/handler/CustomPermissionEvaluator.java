package com.maxmall.common.security.authorize.handler;

import com.google.common.base.Joiner;
import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.security.SecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.io.Serializable;
import java.util.Set;

/**
 * @author ivoter
 * @ClassName CustomPermissionEvaluator.java
 * @date 2019/04/29 18:32:00
 * @Description 自定义permission注解
 */
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {
    private static final Logger log = LoggerFactory.getLogger(CustomPermissionEvaluator.class);

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        String loginName = SecurityUtils.getCurrentLoginName();
        Set<String> currentAuthorityUrl = SecurityUtils.getCurrentAuthorityPermission();
        log.info("验证权限loginName={}, permission={}, hasAuthorityUrl={}", loginName, permission, Joiner.on(GlobalConstant.Symbol.COMMA).join(currentAuthorityUrl));
        // 超级管理员 全部都可以访问
        if (StringUtils.equals(loginName, GlobalConstant.Sys.SUPER_MANAGER_LOGIN_NAME)) {
            return true;
        }
        for (final String authority : currentAuthorityUrl) {
            if (antPathMatcher.match(authority, String.valueOf(permission))) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        throw new UnsupportedOperationException();
    }
}