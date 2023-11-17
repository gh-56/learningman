package com.quiz.learningman.config;

import com.quiz.learningman.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final MemberService memberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // cors 설정 추가
        http.cors( cors -> cors.configurationSource(request -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
            configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT","PATCH","OPTIONS"));
            return configuration;
        }));
//        http.formLogin(
//                form -> form
//                        .loginPage("/login")
//        );
        // auth 설정 추가
        http.authorizeHttpRequests(
                authorize -> authorize
                        .requestMatchers(antMatcher("/")).permitAll()
                        .requestMatchers(antMatcher("/members/**")).permitAll()
                        .requestMatchers(antMatcher("/hello/**")).permitAll()
                        .requestMatchers(antMatcher("/hello2/**")).permitAll()
        );
        return http.build();
    }


}
