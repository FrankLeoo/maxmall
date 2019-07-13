package com.maxmall.common.security;

import com.maxmall.common.core.support.SpringContextHolder;
import com.maxmall.common.security.authorize.AuthorizeConfigManagerImpl;
import com.maxmall.common.security.authorize.DefaultUserDetailsServiceImpl;
import com.maxmall.common.security.authorize.FormAuthenticationManager;
import com.maxmall.common.security.authorize.handler.CustomPermissionEvaluator;
import com.maxmall.common.security.authorize.handler.JsonAuthenticationEntryPoint;
import com.maxmall.common.security.authorize.handler.PcAccessDeniedHandler;
import com.maxmall.common.security.code.SpringSecurityManager;
import com.maxmall.common.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

/**
 * @author ivoter
 * @ClassName SecurityCoreConfig.java
 * @date 2019/04/28 10:37:00
 * @Description security 基础配置
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
@EnableWebSecurity  //启动web安全性
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityCoreConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private FormAuthenticationManager formAuthenticationManager;
    @Autowired
    private SpringSecurityManager validateCodeSecurityManager;
    @Autowired
    private AuthorizeConfigManagerImpl authorizeConfigManager;
    @Autowired
    private PcAccessDeniedHandler pcAccessDeniedHandler;
    @Autowired
    private CustomPermissionEvaluator customPermissionEvaluator;

    /**
     * Configure.
     *
     * @param http the http
     * @throws Exception the exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //配置form登录地址 和方式
        formAuthenticationManager.configure(http);
        http.headers().frameOptions().disable();
        //添加验证码filter登录
        http.apply(validateCodeSecurityManager)
                .and()
                .headers().frameOptions().disable()
                .and()
                .exceptionHandling().authenticationEntryPoint(macLoginUrlAuthenticationEntryPoint())
                .and()
                .exceptionHandling().accessDeniedHandler(pcAccessDeniedHandler)
                .and()
                //权限异常处理
                .csrf().disable();
        //请求接口认证haspermission配置
        authorizeConfigManager.config(http.authorizeRequests());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailsService = SpringContextHolder.getBean(UserDetailsService.class);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        //不删除凭据，以便记住用户
        auth.eraseCredentials(false);
    }

    /**
     * 描述：haspermission验证配置
     *
     * @author ivoter
     * @date 2019/5/22 2:32 PM
     */
    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler(){
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(customPermissionEvaluator);
        return handler;
    }

    /**
     * 默认密码处理器
     *
     * @return 密码加密器
     */
    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 默认认证器
     *
     * @return user details service
     */
    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    public UserDetailsService defaultUserDetailsService() {
        return new DefaultUserDetailsServiceImpl();
    }

    /**
     * 描述：显示声明AuthenticationManager
     *
     * @throws
     * @author ivoter
     * @date 2019/4/28 2:52 PM
     */
    @Override
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationEntryPoint macLoginUrlAuthenticationEntryPoint() {
        return new JsonAuthenticationEntryPoint();
    }

}
