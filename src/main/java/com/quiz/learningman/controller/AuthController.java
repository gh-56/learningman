package com.quiz.learningman.controller;

import com.quiz.learningman.dto.LoginRequestDto;
import com.quiz.learningman.dto.LoginResponseDto;
import com.quiz.learningman.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    // 의존성 주입
    // Jwt 토큰 생성 서비스
    private final JwtService jwtService;
    // 스프링 시큐리티 인증 관리자
    private final AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public LoginResponseDto authenticate(@RequestBody LoginRequestDto loginRequestDto) {
        System.out.println(loginRequestDto);
        // 프론트엔드에서 받은 인증 정보로 인증 토큰을 생성한다.
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword());

        // 인증 토큰에서 인증 정보를 반환한다.
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 인증 정보를 기반으로 토큰을 생성한다.
        String token = jwtService.createToken(authenticate);

        // 인증정보를 받아서 토큰을 반환한다.
        return new LoginResponseDto(token);
    }
}