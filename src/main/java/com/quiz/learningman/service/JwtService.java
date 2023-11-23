package com.quiz.learningman.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtService {
    // JWT 인코더
    private final JwtEncoder jwtEncoder;

    // 토큰 생성
    public String createToken(Authentication authentication){
        // 인가 범위 문자열 반환 : "ROLE_ADMIN ROLE_USER"
        String scope = authentication.getAuthorities() // 인가범위 => 콜렉션
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        // Claim 토큰 조각(권리) 집합을 생성
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("self") // 발행자
                .issuedAt(Instant.now()) // 발행시간 : 현재시간
                .expiresAt(Instant.now().plusSeconds(60 * 30)) // 만료시간 30분
                .subject(authentication.getName())  // 유저의 이름
                .claim("scope", scope)    // 클레임 추가
                .build();
        // ClaimSet을 기반으로 토큰을 암호화하고 문자열로 변환
        return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }
}
