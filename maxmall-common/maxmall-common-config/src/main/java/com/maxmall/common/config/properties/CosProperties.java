package com.maxmall.common.config.properties;

import lombok.Data;

@Data
public class CosProperties {

    /**
     * 是否开启
     */
    private Boolean endable;

    /**
     * 密钥
     */
    private String secretId;

    /**
     * 密钥
     */
    private String secretKey;

    /**
     * 区域
     */
    private String region;
}
