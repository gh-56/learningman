package com.quiz.learningman.service;

import com.quiz.learningman.entity.Homework;
import com.quiz.learningman.entity.Member;
import com.quiz.learningman.repository.HomeworkRepository;
import com.quiz.learningman.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeworkService {
    private final HomeworkRepository homeworkRepository;
    private final MemberRepository memberRepository;

    public Homework saveHomework(Homework homework) {
        return homeworkRepository.save(homework);
    }

    public void memberHomeworkSave(Homework homework){
        List<Member> all = memberRepository.findAll();
        for (Member member : all) {
            member.setHomework(homework);
            member.setDone(false);
            member.setQuizScore(null);
            memberRepository.save(member);
        }
    }
}
