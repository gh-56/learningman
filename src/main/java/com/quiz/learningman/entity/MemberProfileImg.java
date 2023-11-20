package com.quiz.learningman.entity;

import jakarta.persistence.*;

@Entity
public class MemberProfileImg {
    @Id
    @GeneratedValue
    private Long memberImgId;
    // 서버 이미지 파일 이름
    @Column
    private String imgName;

    // 원본 파일 이름
    @Column
    private String oriImgName;

    // 이미지 조회 URL
    @Column
    private String imgUrl;

    public void updateMemberImg(String oriImgName, String imgName, String imgUrl ){
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
}
