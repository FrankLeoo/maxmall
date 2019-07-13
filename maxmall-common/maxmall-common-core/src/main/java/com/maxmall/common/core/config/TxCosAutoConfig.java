package com.maxmall.common.core.config;

import com.maxmall.common.config.properties.MaxmallProperties;
import com.maxmall.common.core.support.TxCosManager;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;

/**
 * 腾讯对象存储
 */
@Configuration
@ConditionalOnProperty(name = "maxmall.cos.endable",havingValue = "true",matchIfMissing = false)
public class TxCosAutoConfig {

    @Resource
    private MaxmallProperties maxmallProperties;

    @Bean
    public COSClient cosClient() {
        COSCredentials cred = new BasicCOSCredentials(maxmallProperties.getCos().getSecretId(), maxmallProperties.getCos().getSecretKey());
        Region region = new Region(maxmallProperties.getCos().getRegion());
        ClientConfig clientConfig = new ClientConfig(region);
        COSClient cosClient = new COSClient(cred, clientConfig);

        return cosClient;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        factory.setMaxFileSize("10240KB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }

    /**
     * 腾讯cos工具manager
     * @return
     */
    @Bean
    public TxCosManager txCosManager(){
        return new TxCosManager();
    }
}
