package com.woorifisa.backend.common.security.springsecurity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// @Configuration annotation이 @EnableWebSecurity에 포함되어 있음
@EnableWebSecurity
public class SecurityConfig {
    // 대칭키
    @Value("${symmetric.key}")
    private String symmetrickey;

    // PasswordEncoder interface의 구현체가 BCryptPasswordEncoder임을 수동 빈 등록을 통해 명시
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // AesBytesEncryptor 사용을 위한 Bean등록
    @Bean
    public AesBytesEncryptor aesBytesEncryptor() {
        return new AesBytesEncryptor(symmetrickey,"70726574657374");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable();
        // CSRF(크로스 사이트 요청 위조)라는 인증된 사용자를 이용해
        // 서버에 위험을 끼치는 요청들을 보내는 공격을 방어하기 위해 post 요청마다 token이 필요한 과정을 생략하겠음을 의미
        return http.build();
    }
}