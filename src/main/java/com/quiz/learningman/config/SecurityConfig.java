package com.quiz.learningman.config;

import com.quiz.learningman.service.MemberService;
import lombok.RequiredArgsConstructor;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
        // UserDetailService를 구현한 서비스 객체 등록
        private final MemberService memberService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http// auth 설정 추가
        .csrf(AbstractHttpConfigurer::disable) // CSRF 끄기 (POST 요청에 csrf 필터가 있어서 컨트롤러에 도달하기 전에 403 응답이 온 것으로 예상)
        .cors(Customizer.withDefaults())
        .sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        // OAuth 2.0 리소스 서버 사용 : JWT 요청 유효성 검사 기본 설정
        http.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
        http
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                // 프리플라이트 요청에 대한 허용
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .requestMatchers(antMatcher("/")).permitAll()
                                .requestMatchers(antMatcher("/css/**")).permitAll()
                                .requestMatchers(antMatcher("/images/**")).permitAll()
                                .requestMatchers(antMatcher("/authenticate")).permitAll()
                                .requestMatchers(antMatcher("/members/**")).permitAll()
                                .requestMatchers(antMatcher("/login/**")).permitAll()
                                .requestMatchers(antMatcher("/memberinfo")).permitAll()
                                .anyRequest().authenticated()
                );
        return http.build();
    }

    // 인증 관리자 커스터마이징
    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder, MemberService memberService){
        // 인증 공급자 구현체
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        // 인증 공급자에 대한 비밀번호 암호화
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        // 인증 공급자에서 사용하는 UserDetailService
        authenticationProvider.setUserDetailsService(memberService);
        return new ProviderManager(authenticationProvider);
    }
//
    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
