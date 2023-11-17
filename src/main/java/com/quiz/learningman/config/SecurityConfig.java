package com.quiz.learningman.config;

import com.quiz.learningman.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
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
//        http.cors( cors -> cors.configurationSource(request -> {
//            CorsConfiguration configuration = new CorsConfiguration();
//            configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//            configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT","PATCH","OPTIONS"));
//            return configuration;
//        }));
        // 로그인 관련 설정
//        http.formLogin(
//                form -> form
//                        .loginPage("/login")  // 기본 로그인 페이지 URL을 설정
//                        .defaultSuccessUrl("/")      // 로그인에 성공했을 때 URL
//                        .usernameParameter("email")  // 로그인에 사용할 매개변수 username -> email
//                        .failureUrl("/members/login/error") // 실패했을 때 보낼 URL
//        );
//
//        // 로그아웃 관련 설정
//        http.logout(
//                logout -> logout
//                        .logoutRequestMatcher(
//                                // Ant 패턴 경로 문법 -> 해당 URL 접속 시 로그아웃됨
//                                antMatcher("/members/logout"))
//                        // 로그아웃이 성공한 경우 메인 페이지로 리다이렉트
//                        .logoutSuccessUrl("/")
//        );
        // auth 설정 추가
        http.authorizeHttpRequests(
                authorize -> authorize
                        // 프리플라이트 요청에 대한 허용
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(antMatcher("/")).permitAll()
                        .requestMatchers(antMatcher("/members/**")).permitAll()
                        .requestMatchers(antMatcher("/login/**")).permitAll()
                        .requestMatchers(antMatcher("/hello/**")).permitAll()
                        .requestMatchers(antMatcher("/hello2/**")).permitAll()
        );

        http.cors(Customizer.withDefaults());
        return http.build();
    }


}
