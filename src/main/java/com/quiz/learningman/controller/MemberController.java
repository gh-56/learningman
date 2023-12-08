// src/main/java/com.demogroup.demoweb/Controller/HelloWorldController.java
package com.quiz.learningman.controller;

import com.quiz.learningman.dto.MemberDto;
import com.quiz.learningman.dto.MemberProfileImgDto;
import com.quiz.learningman.entity.Member;
import com.quiz.learningman.entity.MemberProfileImg;
import com.quiz.learningman.repository.MemberImgRepository;
import com.quiz.learningman.repository.MemberRepository;
import com.quiz.learningman.service.MemberImgService;
import com.quiz.learningman.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberImgService memberImgService;
    private final MemberImgRepository memberImgRepository;

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/members/register")
    public ResponseEntity memberForm(@Valid @RequestBody MemberDto memberDto,
                                     BindingResult bindingResult) {
        // 회원가입시 형식에 맞지 않는 데이터가 들어왔을 때
        if (bindingResult.hasErrors()) {
            String errorMessage = "잘못된 접근입니다";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
        Member member = new Member();
        try {
            member = Member.createMember(memberDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            System.out.println("중복 이메일 입니다!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }

    @PostMapping("/members/update")
    public ResponseEntity memberUpdate(@Valid @RequestBody MemberDto memberDto,
                                       Principal principal) {
        String email = principal.getName();
        Member updateMember = memberRepository.findByMemberEmail(email);
        try {
            memberService.updateMemberInfo(memberDto, passwordEncoder, updateMember);
        } catch (IllegalStateException e) {
            System.out.println("중복 이메일 입니다!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(updateMember);
    }

    // UserDetails : 사용자의 정보를 담는 인터페이스
    @PostMapping("/members/login")
    public UserDetails login(@RequestBody MemberDto memberDto) {
        UserDetails userDetails = memberService.loadUserByUsername(memberDto.getMemberEmail());
        return userDetails;
    }

    @PostMapping("/members/profile/img")
    public ResponseEntity<String> profileImg(MemberProfileImg memberProfileImg,
                                             @RequestParam("file") MultipartFile file,
                                             Principal principal) {
        String email = principal.getName();
        Member byMemberEmail = memberRepository.findByMemberEmail(email);
        MemberProfileImg beforeImg = new MemberProfileImg();
        if (byMemberEmail.getMemberProfileImg() != null) {
            beforeImg = byMemberEmail.getMemberProfileImg();
        }
        try {
            String imgUrl = memberImgService.saveMemberImg(memberProfileImg, file);
            memberService.updateMemberProfile(byMemberEmail, memberProfileImg);
            // 이전 이미지가 있다면 삭제
            if (byMemberEmail.getMemberProfileImg() != null) {
                memberImgRepository.delete(beforeImg);
            }
            return ResponseEntity.status(HttpStatus.OK).body(imgUrl);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }

    @GetMapping("/members/profile/baseimg")
    public ResponseEntity<String> profileBaseImg(Principal principal) {
        //System.out.println("principal"+principal.getName()); // test@test.com
        String email = principal.getName();
        Member byMemberEmail = memberRepository.findByMemberEmail(email);
        MemberProfileImg memberProfileImg = byMemberEmail.getMemberProfileImg();
        Long memberImgId = memberProfileImg.getMemberImgId();
        try {
            String imgUrl = memberImgService.baseImg(memberImgId);
            System.out.println("imgUrl: " + imgUrl);
            return ResponseEntity.status(HttpStatus.OK).body(imgUrl);
        } catch (Exception e) {
            System.out.println("e" + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
