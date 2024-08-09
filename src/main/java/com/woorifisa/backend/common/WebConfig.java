package com.woorifisa.backend.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로에 대해 CORS를 허용합니다
            .allowedOrigins("http://localhost:3000") // 허용할 출처를 설정합니다
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드를 설정합니다
            .allowedHeaders("Content-Type", "Authorization", "X-Requested-With") // 허용할 요청 헤더를 설정합니다
            .allowCredentials(true); // 자격 증명 포함을 허용합니다 (예: 쿠키)
    }
}
