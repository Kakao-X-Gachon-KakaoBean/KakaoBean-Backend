package com.kakaobean.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class VerifyEmailRequest {

    @Email
    private String email;

    @NotBlank
    private String authKey;

    public VerifyEmailRequest(String email,
                              String authKey) {
        this.email = email;
        this.authKey = authKey;
    }
}
