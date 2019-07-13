package com.maxmall.common.config.properties;


import com.maxmall.common.base.constant.GlobalConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The class Maxmall properties.
 *
 * @author maxmall.net @gmail.com
 */
@Data
@ConfigurationProperties(prefix = GlobalConstant.ROOT_PREFIX)
public class MaxmallProperties {
	private AsyncTaskProperties task = new AsyncTaskProperties();
	private SwaggerProperties swagger = new SwaggerProperties();
	private QiniuProperties qiniu = new QiniuProperties();
	private JobProperties job = new JobProperties();
	private ZookeeperProperties zk = new ZookeeperProperties();
	private CosProperties cos = new  CosProperties();
}
