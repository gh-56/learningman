package com.quiz.learningman.entity;

import com.quiz.learningman.constant.Role;
import com.quiz.learningman.dto.MemberDto;
import com.quiz.learningman.dto.MemberProfileImgDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
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

    public  static MemberProfileImg createMemberProfileImg(MemberProfileImgDto memberProfileImgDto /*PasswordEncoder passwordEncoder*/){
        MemberProfileImg memberProfileImg = new MemberProfileImg();

        memberProfileImg.setImgName(memberProfileImgDto.getImgName());
        memberProfileImg.setOriImgName(memberProfileImgDto.getOriImgName());
        memberProfileImg.setImgUrl(memberProfileImgDto.getImgUrl());

        return memberProfileImg;
    }
}
