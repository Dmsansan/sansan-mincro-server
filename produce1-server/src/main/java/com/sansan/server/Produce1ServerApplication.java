package com.sansan.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Produce1ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Produce1ServerApplication.class, args);
    }

}
