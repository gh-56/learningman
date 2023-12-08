package com.quiz.learningman.dto;

import com.quiz.learningman.constant.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor
public class MemberDto {
    Long id;

    String memberName;

    String memberEmail;

    String memberPassword;

    String role;

}
