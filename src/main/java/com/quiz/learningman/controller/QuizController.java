package com.quiz.learningman.controller;

import com.quiz.learningman.dto.BookDto;
import com.quiz.learningman.dto.HomeworkDto;
import com.quiz.learningman.dto.ScoreDto;
import com.quiz.learningman.entity.Homework;
import com.quiz.learningman.entity.Member;
import com.quiz.learningman.repository.MemberRepository;
import com.quiz.learningman.repository.QuizRepository;
import com.quiz.learningman.service.HomeworkService;
import com.quiz.learningman.service.MemberService;
import com.quiz.learningman.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;
    private final HomeworkService homeworkService;
    private final MemberRepository memberRepository;
    private final QuizRepository quizRepository;
    private final MemberService memberService;

    @GetMapping(value = "/book")
    public ResponseEntity<List<String>> getBookList() {
        return ResponseEntity.ok(quizService.getBooks());
    }

    @PostMapping(value = "/book/chapter")
    public ResponseEntity getChapterNames(@RequestBody BookDto book) {
        List<String> chaptersByBook = quizService.getChaptersByBook(book.getSelectedBook());
        System.out.println("chaptersByBook : " + chaptersByBook);
        System.out.println(chaptersByBook.size());
        try {
            return ResponseEntity.status(HttpStatus.OK).body(chaptersByBook);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/quiz/setting")
    public ResponseEntity quizSetting(@RequestBody HomeworkDto homeworkDto){
        Homework homework = Homework.createHomework(homeworkDto);
        homeworkService.saveHomework(homework);
        homeworkService.memberHomeworkSave(homework);
        return ResponseEntity.status(HttpStatus.OK).body(homework);
    }

    @GetMapping("/quiz/test")
    public ResponseEntity<List<Object[]>> quizExport(Principal principal){
        String email = principal.getName();
        Member member = memberRepository.findByMemberEmail(email);
        Homework homework = member.getHomework();
        List<Object[]> quizList = quizRepository.findKorAndEngByBookAndChapter(homework.getHomeworkBook(), homework.getHomeworkChapter());
        return ResponseEntity.status(HttpStatus.OK).body(quizList);
    }

    @PostMapping("/quiz/end")
    public void quizEnd(Principal principal, @RequestBody ScoreDto score){
        String email = principal.getName();
        String getScore = score.getScore();
        memberService.updateQuizScore(email, getScore);
    }
}
