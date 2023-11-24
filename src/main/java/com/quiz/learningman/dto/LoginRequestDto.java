package com.quiz.learningman.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequestDto {
    String memberEmail;
    String memberPassword;
}
