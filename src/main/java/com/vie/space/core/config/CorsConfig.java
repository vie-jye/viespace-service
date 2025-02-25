package com.vie.space.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");  // 允许所有来源
        config.addAllowedMethod("*");  // 允许所有请求方法（GET、POST等）
        config.addAllowedHeader("*");  // 允许所有请求头
        config.setAllowCredentials(true);   // 允许携带认证信息（如 Cookie）
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}

