package com.quiz.learningman.service;

import com.quiz.learningman.dto.MemberDto;
import com.quiz.learningman.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    // 멤버 테스트용 given 생성하는 메서드
    private static Member createMember() {
        MemberDto memberDto = MemberDto.builder()
                .memberEmail("test@test.com")
                .memberName("김테스터")
                .memberPassword("12345")
                .build();
        Member member = Member.createMember(memberDto);
        return member;
    }

    private static Member createMember2() {
        MemberDto memberDto = MemberDto.builder()
                .memberEmail("test~test.com")
                .memberName("김테스터")
                .memberPassword("12345")
                .build();
        Member member = Member.createMember(memberDto);
        return member;
    }
    @Test
    @DisplayName("이메일 형식 일치 여부 테스트")
    void saveMember() {
        // given
        Member member = createMember2();

        // when
        Member savedMember = memberService.saveMember(member);

        // then
        Assertions.assertThat(savedMember.getMemberEmail()).isEqualTo(member.getMemberEmail());

    }

    @Test
    @DisplayName("중복 회원 가입 테스트")
    void duplicatedMember() {
        // given
        Member member1 = createMember();
        Member member2 = createMember();
        memberService.saveMember(member1);

        // when
        // assertThrows(예외타입, 실행문) : 실행시 해당 예외타입이 발생하면 검증 통과
        Throwable e = assertThrows(IllegalStateException.class, () -> {
            memberService.saveMember(member2);
        });

        // then
        assertThat(e.getMessage()).isEqualTo("가입된 회원입니다.");
    }

}