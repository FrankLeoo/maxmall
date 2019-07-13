 package com.maxmall.common.security.config;

 import com.maxmall.common.security.authorize.JwtTokenManager;
 import com.maxmall.common.security.properties.SecurityProperties;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;


 /**
  * The class Token store config.
  *
  * @author maxmall.net@gmail.com
  */
 @Configuration
 public class TokenStoreConfig {

     @Autowired
     private SecurityProperties securityProperties;


     @Bean
     public JwtTokenManager jwtTokenManager(){
         return new JwtTokenManager();
     }

 }
