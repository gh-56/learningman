// src/main/java/com.demogroup.demoweb/Controller/HelloWorldController.java
package com.quiz.learningman.controller;

import com.quiz.learningman.dto.MemberDto;
import com.quiz.learningman.entity.Member;
import com.quiz.learningman.entity.MemberProfileImg;
import com.quiz.learningman.service.MemberImgService;
import com.quiz.learningman.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberImgService memberImgService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/members/register")
    public ResponseEntity memberForm(@Valid @RequestBody MemberDto memberDto,
                                     BindingResult bindingResult){
        // 회원가입시 형식에 맞지 않는 데이터가 들어왔을 때
        if (bindingResult.hasErrors()){
            String errorMessage = "잘못된 접근입니다";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
        Member member = new Member();
        try{
            member = Member.createMember(memberDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            System.out.println("중복 이메일 입니다!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(member);
}

    // UserDetails : 사용자의 정보를 담는 인터페이스
    @PostMapping("/members/login")
    public UserDetails login(@RequestBody MemberDto memberDto){
        System.out.println(memberDto);
        UserDetails userDetails = memberService.loadUserByUsername(memberDto.getMemberEmail());
        return userDetails;
    }

    @PostMapping("/members/profile")
    public ResponseEntity<String> profile(MemberProfileImg memberProfileImg, @RequestParam("file") MultipartFile file){
        try {
            memberImgService.saveMemberImg(memberProfileImg, file);
            return ResponseEntity.status(HttpStatus.OK).body(memberProfileImg.getImgUrl());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }


}
