package com.example.legateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LeGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/le-user/**").uri("http://localhost:8001"))
                .route("blog-service", r -> r.path("/le-blog/**").uri("http://localhost:8002"))
                .build();
    }

}
