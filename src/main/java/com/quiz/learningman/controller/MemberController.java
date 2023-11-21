// src/main/java/com.demogroup.demoweb/Controller/HelloWorldController.java
package com.quiz.learningman.controller;

import com.quiz.learningman.dto.MemberDto;
import com.quiz.learningman.dto.MemberProfileImgDto;
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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberImgService memberImgService;

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
            member = Member.createMember(memberDto);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            System.out.println("중복 이메일 입니다!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(member);
}
//        @PostMapping("/members/register")
//    public String memberForm(@Valid @RequestBody MemberDto memberDto, BindingResult bindingResult){
//        // 회원가입시 형식에 맞지 않는 데이터가 들어왔을 때
//        if (bindingResult.hasErrors()){
//            String errorMessage = "잘못된 접근입니다";
//            return errorMessage;
//        }
//        Member member = new Member();
//        try{
//            member = Member.createMember(memberDto);
//            memberService.saveMember(member);
//        } catch (IllegalStateException e) {
//            System.out.println("중복 이메일 입니다!");
//            return "안녕";
//        }
//        return "member";
//    }


    // UserDetails : 사용자의 정보를 담는 인터페이스
    @PostMapping("/members/login")
    public UserDetails login(@RequestBody MemberDto memberDto){
        UserDetails userDetails = memberService.loadUserByUsername(memberDto.getMemberEmail());
        return userDetails;
    }

    @GetMapping("/images/**")
    public ResponseEntity<String> image(){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @PostMapping("/members/profile")
    public ResponseEntity<String> profile(MemberProfileImg memberProfileImg, @RequestParam("file") MultipartFile file){
//        if (bindingResult.hasErrors()){
//            String errorMessage = "잘못된 접근입니다";
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
//        }
//        MemberProfileImg memberProfileImg = MemberProfileImg.createMemberProfileImg(memberProfileImgDto);
//        try {
//            memberImgService.saveMemberImg(memberProfileImg, memberImgFile);
//        } catch (Exception e) {
//            System.out.println("try문");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(memberProfileImg);
        try {
            // You can perform further processing with the uploaded file here
            // For demonstration purposes, this example simply returns the file name
            memberImgService.saveMemberImg(memberProfileImg, file);
//            String fileName = file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(memberProfileImg.getImgUrl());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }


}
