package com.project.leuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.project.leuser.mapper")
@SpringBootApplication
public class LeUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeUserApplication.class, args);
    }
}
