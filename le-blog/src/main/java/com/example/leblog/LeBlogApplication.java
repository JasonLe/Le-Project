package com.example.leblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.leblog.mapper")
@SpringBootApplication
public class LeBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeBlogApplication.class, args);
    }

}
