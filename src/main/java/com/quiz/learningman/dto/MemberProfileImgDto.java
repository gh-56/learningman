package com.quiz.learningman.dto;

import com.quiz.learningman.constant.Role;
import com.quiz.learningman.entity.Member;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberProfileImgDto {
    private Long memberImgId;
    // 서버 이미지 파일 이름
    private String imgName;

    // 원본 파일 이름
    private String oriImgName;

    // 이미지 조회 URL
    private String imgUrl;


}
