package com.maxmall.common.config;


import com.maxmall.common.config.properties.MaxmallProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The class Aliyun core config.
 *
 * @author maxmall.net @gmail.com
 */
@Configuration
@EnableConfigurationProperties(MaxmallProperties.class)
public class MaxmallCoreConfig {
}
