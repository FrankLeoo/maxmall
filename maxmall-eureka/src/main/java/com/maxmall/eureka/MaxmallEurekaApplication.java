package com.maxmall.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * The class Maxmall Master eureka application.
 *
 * @author maxmall.net@gmail.com
 */
@EnableEurekaServer
@SpringBootApplication
public class MaxmallEurekaApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        SpringApplication.run(MaxmallEurekaApplication.class, args);
    }
}
