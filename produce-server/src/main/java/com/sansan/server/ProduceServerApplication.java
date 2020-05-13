package com.sansan.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author siss
 * @date 2020/05/11
 */
@SpringBootApplication
@EnableEurekaClient
public class ProduceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProduceServerApplication.class, args);
    }

}
