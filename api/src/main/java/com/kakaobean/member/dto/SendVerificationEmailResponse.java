package com.kakaobean.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SendVerificationEmailResponse {

    private String email;

    public SendVerificationEmailResponse(String email) {
        this.email = email;
    }
}
