package com.quiz.learningman.entity;

import com.quiz.learningman.constant.Role;
import com.quiz.learningman.dto.MemberDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;


/** 멤버 | Member */

@Entity
@Getter
@Setter
@ToString
public class Member {
    // 멤버 아이디
    @Id
    @Column
    @GeneratedValue
    private Long memberId;

    // 이메일
    @Column(unique = true)
    private String memberEmail;

    // 비밀번호
    private String memberPassword;

    // 이름
    private String memberName;

    // 역할
    @Column(name = "role" ,length = 128)
    private Role role;

    public  static Member createMember(MemberDto memberDto, PasswordEncoder passwordEncoder){
        Member member = new Member();

        member.setMemberName(memberDto.getMemberName());
        member.setMemberEmail(memberDto.getMemberEmail());
        String encodedPassword = passwordEncoder.encode(memberDto.getMemberPassword());
        member.setMemberPassword(encodedPassword);
        member.setRole(Role.ADMIN);

        return member;
    }
}

