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
    public String createToken(Authentication authenticate) {
        // 인가 범위 문자열 반환 : "ROLE_ADMIN ROLE USER"
        String scope = authenticate.getAuthorities() // 인가 범위 => 콜렉션
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        // Claim 토큰 조각(권리) 집합을 생성
        JwtClaimsSet claimSet = JwtClaimsSet.builder()
                .issuer("self")             // 발행자
                .issuedAt(Instant.now())    // 발행 시간 : 현재 시간
                .expiresAt(Instant.now().plusSeconds(60*60)) // 만료시간 60분
                .subject(authenticate.getName())        // 유저의 이름
                .claim("scope", scope)              // 클레임 추가 (인가 권한)
                .build();

        // ClaimSet을 기반으로 토큰을 암호화하고 문자열로 반환
        String token = jwtEncoder.encode(JwtEncoderParameters.from(claimSet)).getTokenValue();

        return token;
    }
}
