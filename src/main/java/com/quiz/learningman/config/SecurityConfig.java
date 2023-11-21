package com.quiz.learningman.config;

import com.quiz.learningman.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


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
                                .anyRequest().authenticated()
                );
        return http.build();
    }

//    @Bean
//    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http// auth 설정 추가
//        .csrf(AbstractHttpConfigurer::disable)
//        .cors(Customizer.withDefaults());
//        http.authorizeHttpRequests(
//                authorize -> authorize
//                        // 프리플라이트 요청에 대한 허용
//                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                        .requestMatchers(antMatcher("/")).permitAll()
//                        .requestMatchers(antMatcher("/css/**")).permitAll()
//                        .requestMatchers(antMatcher("/images/**")).permitAll()
//                        .requestMatchers(antMatcher("/members/**")).permitAll()
//                        .requestMatchers(antMatcher("/login/**")).permitAll()
//                        .anyRequest().authenticated()
//        );
//        http.authorizeHttpRequests().anyRequest().authenticated();
//
//        // 로그인 사용
//        http.formLogin();
////        http.csrf(csrf -> csrf.disable()); // CSRF 끄기 (POST 요청에 csrf 필터가 있어서 컨트롤러에 도달하기 전에 403 응답이 온 것으로 예상)
//        return http.build();
//    }
}
