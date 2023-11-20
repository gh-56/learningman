package com.quiz.learningman.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${uploadPath}") // application.properties에 등록된 값(업로드 경로) 읽어옴
    String uploadPath;


    // 정적인 Resource 를 처리하기 위해 사용되는 Handler
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**") // 어느 경로로 들어왔을 때 매핑이 되어줄 것인지를 정의
                .addResourceLocations(uploadPath); // 실제 파일이 있는 경로
    }
}
