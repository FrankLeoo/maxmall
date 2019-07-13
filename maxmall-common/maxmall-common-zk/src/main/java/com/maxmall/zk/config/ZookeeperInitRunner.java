package com.maxmall.zk.config;

import com.maxmall.common.config.properties.MaxmallProperties;
import com.maxmall.common.config.properties.ZookeeperProperties;
import com.maxmall.zk.registry.RegistryCenterFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * The class Redis init runner.
 *
 * @author maxmall.net @gmail.com
 */
@Component
@Order
@Slf4j
public class ZookeeperInitRunner implements CommandLineRunner {
	@Resource
	private MaxmallProperties maxmallProperties;
	@Value("${spring.application.name}")
	private String applicationName;

	/**
	 * Run.
	 *
	 * @param args the args
	 *
	 * @throws Exception the exception
	 */
	@Override
	public void run(String... args) throws Exception {
		String hostAddress = InetAddress.getLocalHost().getHostAddress();
		log.info("###ZookeeperInitRunner，init. HostAddress={}, applicationName={}", hostAddress, applicationName);
		RegistryCenterFactory.startup(maxmallProperties, hostAddress, applicationName);
		log.info("###ZookeeperInitRunner，finish<<<<<<<<<<<<<");

	}


	public static void main(String[] args) throws UnknownHostException {
		ZookeeperProperties zk = new ZookeeperProperties();
		zk.setZkAddressList("148.70.244.148:2181");
		MaxmallProperties maxmallProperties = new MaxmallProperties();
		maxmallProperties.setZk(zk);
		String hostAddress = InetAddress.getLocalHost().getHostAddress();
		log.info("###ZookeeperInitRunner，init. HostAddress={}, applicationName={}", hostAddress, "test");
		RegistryCenterFactory.startup(maxmallProperties, hostAddress, "test");
		log.info("###ZookeeperInitRunner，finish<<<<<<<<<<<<<");
	}

}
