package com.example.sukashii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableCaching
public class SukashiiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SukashiiApplication.class, args);
    }

}