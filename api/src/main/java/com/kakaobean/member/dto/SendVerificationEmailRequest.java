package com.kakaobean.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SendVerificationEmailRequest {

    private String email;

    public SendVerificationEmailRequest(String email) {
        this.email = email;
    }
}
