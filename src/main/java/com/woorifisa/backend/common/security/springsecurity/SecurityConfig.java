package com.woorifisa.backend.common.security.springsecurity;

import org.springframework.beans.factory.annotation.Value;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import jakarta.servlet.http.HttpServletResponse;

// @Configuration annotation이 @EnableWebSecurity에 포함되어 있음
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${encryption.password.key}")
    private String passwordKey;

    @Value("${encryption.salt}")
    private String salt;


    // PasswordEncoder interface의 구현체가 BCryptPasswordEncoder임을 수동 빈 등록을 통해 명시
    // 이를 통해 의존성을 주입 받아 사용 가능
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // AesBytesEncryptor 사용을 위한 Bean등록
    @Bean
    AesBytesEncryptor aesBytesEncryptor() {
        return new AesBytesEncryptor(passwordKey, salt);
    }

   

    @Bean
    AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            // 로그인한 사용자 정보 가져오기
            Object principal = authentication.getPrincipal();
            
            if (principal instanceof MemberDetail) {
                MemberDetail memberDetail = (MemberDetail) principal;
    
                // 사용자 정보 가져오기
                String memId = memberDetail.getUsername();       // memId (username 대신 사용)
                Collection<? extends GrantedAuthority> roles = memberDetail.getAuthorities(); // memType 역할 정보
                String memNum = memberDetail.getMemNum();        // memNum 정보 가져오기
                String rolesString = roles.stream()
                                      .map(GrantedAuthority::getAuthority)
                                      .collect(Collectors.joining(", "));
    
                // JSON으로 응답
                response.setContentType("application/json;charset=UTF-8");
                response.setStatus(HttpServletResponse.SC_OK);
    
                // 사용자 정보 JSON으로 작성
                String jsonResponse = String.format(
                    "{\"message\": \"Login successful\", \"memId\": \"%s\", \"memType\": \"%s\", \"memNum\": \"%s\"}",
                    memId,                          // memId 값
                    rolesString,               // memType 역할 정보를 문자열로 변환
                    memNum                          // memNum 값 추가
                );


                System.out.println("Response JSON: " + jsonResponse);
    
                response.getWriter().write(jsonResponse);
                response.getWriter().flush();
            } else {
                // Principal이 예상한 타입이 아닐 때의 처리
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("{\"message\": \"Unexpected authentication principal\"}");
                response.getWriter().flush();
            }
        };
    }

    

    private static final String[] AUTHENTICATED_URLS = 
        {  
            "/auth/sessionInfo",
            "/member/**",
            "/main/subscriptionInsert",
            "/main/reviewInsert/reviewInsert",
            "/main/reviewDelete",
            "/main/paymentInsert",
            "/main/paymentAllByMember"
        };

    private static final String[] SWAGGER_URLS = 
        {
            "/swagger-ui/**"
            , "/v3/api-docs/**"
            // 기타 추가해야할 것들 추가
        };

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(requests -> requests
            .requestMatchers(AUTHENTICATED_URLS).authenticated()
            .requestMatchers("/admin/**").hasRole("9")
            .requestMatchers("/products/**").hasAnyRole("2","9")
            .requestMatchers(SWAGGER_URLS).permitAll()
            .anyRequest().permitAll()
            )
            .csrf(csrf -> csrf.disable())
            .formLogin(formLogin ->
                formLogin
                    .loginProcessingUrl("/auth/login")  // 로그인 처리 URL
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .successHandler(successHandler())
                    .failureHandler((request, response, exception) -> {
                        System.err.println("Login failed: " + exception.getMessage()); // 로그에 실패 원인 기록
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                    })
                    .permitAll()
            )
                    .logout(logout -> logout
                    .logoutUrl("/auth/logout")  // 로그아웃 URL을 "/custom-logout"으로 설정
                    .logoutSuccessHandler((request, response, authentication) -> {
                        // 로그아웃 성공 시 로그를 기록
                        if (authentication != null) {
                            System.out.println("User " + authentication.getName() + " logged out successfully.");
                        } else {
                            System.out.println("Logout request received but no authenticated user.");
                        }
                        response.setStatus(HttpServletResponse.SC_OK);
                    })
                    .invalidateHttpSession(true)  // 세션 무효화
                    .deleteCookies("JSESSIONID")  // 쿠키 삭제
                    .permitAll()
                
            )
            .sessionManagement(sessionManagement ->
                sessionManagement
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .sessionFixation().newSession()
            );

        return http.build();
    }
}