package com.quiz.learningman.config;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;


import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Configuration
public class JwtConfig {

    // 비대칭 키 (공개키-개인키) 키 페어 생성하기
    @Bean
    public KeyPair keyPair(){
        // RSA 2048 알고리즘으로 키 페어 생성
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
    // RSA Key 쌍 만들기 OAuth2 리소스 서버의 Nimus 라이브러리 사용
    @Bean
    public RSAKey rsaKey(KeyPair keyPair){
        return new RSAKey.Builder((RSAPublicKey) keyPair.getPublic()) // 공개키
                .privateKey(keyPair().getPrivate())     // 비공개키
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    // RSA -> Json Web Key 소스 생성
    @Bean
    public JWKSource jwkSource(RSAKey rsaKey){
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, context) -> jwkSelector.select(jwkSet);
    }

    // JWT 키 암호화 (생성) : nimbus
    @Bean
    public JwtEncoder jwtEncoder(JWKSource jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }

    // JWT 키 복호화 (검증) : nimbus
    @Bean
    public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException{
        return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();
    }
}
