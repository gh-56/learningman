package com.quiz.learningman.service;

import com.quiz.learningman.constant.Role;
import com.quiz.learningman.entity.Member;
import com.quiz.learningman.entity.MemberProfileImg;
import com.quiz.learningman.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TeacherService {

    private final MemberRepository memberRepository;

    public List<Member> findStudent(){
        List<Member> allMember = memberRepository.findAll();
        List<Member> student = new ArrayList<>();
        for (Member member : allMember) {
            if(member.getRole().equals(Role.STUDENT)){
                student.add(member);
            }
        }
        return student;
    }
}
