package com.maxmall.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * The class Maxmall Master discovery application.
 *
 * @author maxmall.net@gmail.com
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class MaxmallDiscoveryApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(MaxmallDiscoveryApplication.class, args);
	}
}
