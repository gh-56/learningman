// src/main/java/com.demogroup.demoweb/Controller/HelloWorldController.java
package com.quiz.learningman.controller;

import com.quiz.learningman.dto.MemberDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class MemberController {
    @PostMapping("/members")
    public void memberForm(@RequestBody MemberDto memberDto){
        System.out.println(memberDto);

    }
}
