package com.quiz.learningman.controller;

import com.quiz.learningman.dto.LoginRequestDto;
import com.quiz.learningman.dto.LoginResponseDto;
import com.quiz.learningman.service.JwtService;
import com.quiz.learningman.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final MemberService memberService;
    // 의존성 주입
    // Jwt 토큰 생성 서비스
    private final JwtService jwtService;
    // 스프링 시큐리티 인증 관리자
    private final AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public LoginResponseDto authenticate(@RequestBody LoginRequestDto loginRequestDto){
        System.out.println(loginRequestDto);

        UserDetails userDetails = memberService.loadUserByUsername(loginRequestDto.getMemberEmail());
        if(userDetails == null){
            return null;
        }

        log.info(loginRequestDto.toString());

        // 프론트에서 받은 인증 정보로 인증 토큰을 생성한다
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getMemberEmail(), loginRequestDto.getMemberPassword());
        System.out.println(authenticationToken);

        // 인증 토큰에서 인증 정보를 반환한다.
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 인증 정보를 기반으로 토큰을 생성한다.
        String token = jwtService.createToken(authenticate);
        log.info(token);

        // 인증정보를 받아서 토큰을 반환한다.
        return new LoginResponseDto(token);
    }
}