package com.template.core.service.member;

import lombok.Getter;

@Getter
public class RegisterMemberRequestDto {

    private final String email;
    private final String password;

    public RegisterMemberRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
