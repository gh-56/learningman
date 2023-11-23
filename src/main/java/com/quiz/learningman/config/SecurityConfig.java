package com.quiz.learningman.config;

import com.quiz.learningman.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.Arrays;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http// auth 설정 추가
        .csrf(AbstractHttpConfigurer::disable)
        .cors(Customizer.withDefaults());
        http
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .requestMatchers(antMatcher("/**")).permitAll()
                                .requestMatchers(antMatcher("/css/**")).permitAll()
                                .requestMatchers(antMatcher("/images/**")).permitAll()
                                .requestMatchers(antMatcher("/members/**")).permitAll()
                                .requestMatchers(antMatcher("/login/**")).permitAll()
                                .requestMatchers(antMatcher("/authenticate/**")).permitAll()
                                .anyRequest().authenticated()
                );

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

         http.oauth2ResourceServer((oauth2)-> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }
    // 인증 관리자 커스터마이징
    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder, MemberService memberService){
        // 인증 공급자 구현체
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        // 인증 공급자에 비밀번호 암호화
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        // 인증 공급자에서 사용하는 UserDetailService
        authenticationProvider.setUserDetailsService(memberService);
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
