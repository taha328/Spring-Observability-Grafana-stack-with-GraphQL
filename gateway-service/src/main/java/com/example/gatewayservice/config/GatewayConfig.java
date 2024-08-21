package com.example.gatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user_route", r -> r.path("/users/**")
                        .uri("lb://user-service"))
                .route("product_route", r -> r.path("/products/**")
                        .uri("lb://product-service"))
                .route("order_route", r -> r.path("/orders/**")
                        .uri("lb://orderservice"))
                .build();
    }
}
