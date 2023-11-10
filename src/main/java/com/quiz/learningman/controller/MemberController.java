// src/main/java/com.demogroup.demoweb/Controller/HelloWorldController.java
package com.quiz.learningman.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class MemberController {
    @PostMapping("/members")
    public void memberForm(@RequestBody HashMap<String, Object> map){
        System.out.println(map);

    }
}
