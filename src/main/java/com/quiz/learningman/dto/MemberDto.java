package com.quiz.learningman.dto;

import com.quiz.learningman.constant.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @ToString @Builder
public class MemberDto {
    Long id;

    @NotBlank(message = "필수 입력값입니다")
    String memberName;

    @Email(message = "이메일 형식으로 입력해주세요")
    @NotBlank (message = "필수 입력값입니다")
    String memberEmail;

    @NotBlank(message = "필수 입력값입니다")
    String memberPassword;

    @Enumerated(EnumType.STRING)
    private Role role;

}
