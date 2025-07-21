package com.example.commerceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CommerceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommerceServiceApplication.class, args);
    }

}
