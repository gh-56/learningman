package com.quiz.learningman.config;

import com.quiz.learningman.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final MemberService memberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.formLogin(
                form -> form
                        .loginPage("http://localhost:8080/login/")
        );

        http.authorizeHttpRequests(
                authorize -> authorize
                        .requestMatchers(antMatcher("http://localhost:8080/")).permitAll()
                        .requestMatchers(antMatcher("http://localhost:8080/login/**")).permitAll()
                        .requestMatchers(antMatcher("http://localhost:8080/register/**")).permitAll()
        );
        return http.build();
    }
}
