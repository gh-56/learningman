package com.quiz.learningman.service;

import com.quiz.learningman.dto.MemberDto;
import com.quiz.learningman.entity.Member;
import com.quiz.learningman.entity.MemberProfileImg;
import com.quiz.learningman.repository.MemberImgRepository;
import com.quiz.learningman.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final MemberImgService memberImgService;
    private final MemberImgRepository memberImgRepository;

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByMemberEmail(member.getMemberEmail());
        // 가입을 위해 입력한 멤버가 존재한다면
        if (findMember != null) {
            // 예외 발생
            throw new IllegalStateException("가입된 회원입니다.");
        }
    }

    // 회원가입
    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    public Member updateMemberInfo(MemberDto memberDto,
                                   PasswordEncoder passwordEncoder, Member updateMember) {
        if(memberDto.getMemberName() != null){
            updateMember.setMemberName(memberDto.getMemberName());
        } else if(memberDto.getMemberEmail() != null){
            updateMember.setMemberEmail(null);
            memberRepository.save(updateMember);
            updateMember.setMemberEmail(memberDto.getMemberEmail());
        } else {
            String encodedPassword = passwordEncoder.encode(memberDto.getMemberPassword());
            updateMember.setMemberPassword(encodedPassword);
        }
        return memberRepository.save(updateMember);
    }

    public void updateMemberProfile(Member member, MemberProfileImg memberProfileImg) {
        member.setMemberProfileImg(memberProfileImg);
        memberRepository.save(member);
    }

    public Member memberInfo(String email) {
        Member member = memberRepository.findByMemberEmail(email);
        if (member == null) {
            new RuntimeException("회원을 찾을 수 없습니다");
        }
        return member;
    }

    public void updateQuizScore(String email, String score, List<String> wrongIndexList){
        Member byMemberEmail = memberRepository.findByMemberEmail(email);
        byMemberEmail.setQuizScore(score);
        byMemberEmail.setDone(true);
        byMemberEmail.setWrongIndexList(wrongIndexList);
        memberRepository.save(byMemberEmail);
    }

    @Override
    public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberEmail(memberEmail);

        if (member == null) {
//            return null;
            throw new UsernameNotFoundException(memberEmail);
        }

        return User.builder()
                .username(member.getMemberEmail())
                .password(member.getMemberPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
