package com.quiz.learningman.controller;

import com.quiz.learningman.constant.Role;
import com.quiz.learningman.dto.ScoreDto;
import com.quiz.learningman.entity.Homework;
import com.quiz.learningman.entity.Member;
import com.quiz.learningman.entity.MemberProfileImg;
import com.quiz.learningman.repository.MemberRepository;
import com.quiz.learningman.repository.QuizRepository;
import com.quiz.learningman.service.MemberImgService;
import com.quiz.learningman.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeacherController {

    private final MemberRepository memberRepository;
    private final TeacherService teacherService;
    private final MemberImgService memberImgService;
    private final QuizRepository quizRepository;


    @PostMapping("/teacher/main")
    public ResponseEntity teacherMain(Principal principal) {
        String email = principal.getName();
        Member teacher = memberRepository.findByMemberEmail(email);
        if (teacher.getRole().equals(Role.TEACHER)) {
            List<Member> student = teacherService.findStudent();
            return ResponseEntity.status(HttpStatus.OK).body(student);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/teacher/wrongquiz")
    public ResponseEntity wrongQuizList(Principal principal, @RequestBody ScoreDto scoreDto){
        String email = principal.getName();
        Member member = memberRepository.findByMemberEmail(email);
        List<String> wrongIndexList = scoreDto.getWrongIndexList();
        Homework homework = member.getHomework();
        List<Object[]> quizList = quizRepository.findKorAndEngByBookAndChapter(homework.getHomeworkBook(), homework.getHomeworkChapter());
        List<Object[]> wrongQuiz = new ArrayList<>();
        for (String s : wrongIndexList) {
            wrongQuiz.add(quizList.get(Integer.parseInt(s)));
        }
        return ResponseEntity.status(HttpStatus.OK).body(wrongQuiz);
    }
}
