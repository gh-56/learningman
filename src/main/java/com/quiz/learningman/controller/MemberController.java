// src/main/java/com.demogroup.demoweb/Controller/HelloWorldController.java
package com.quiz.learningman.controller;

import com.quiz.learningman.dto.MemberDto;
import com.quiz.learningman.entity.Member;
import com.quiz.learningman.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members/register")
    public Object memberForm(@Valid @RequestBody MemberDto memberDto, BindingResult bindingResult){
        // 회원가입시 형식에 맞지 않는 데이터가 들어왔을 때
        if (bindingResult.hasErrors()){
            String errorMessage = "잘못된 접근입니다";
            return errorMessage;
        }
        Member member = Member.createMember(memberDto);
        memberService.saveMember(member);
        return member;
    }

    @PostMapping("/members/login")
    public UserDetails login(@RequestBody MemberDto memberDto){
        UserDetails userDetails = memberService.loadUserByUsername(memberDto.getMemberEmail());
        return userDetails;
    }


}
