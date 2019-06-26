package com.sansan.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Produce2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Produce2ServerApplication.class, args);
    }

}
