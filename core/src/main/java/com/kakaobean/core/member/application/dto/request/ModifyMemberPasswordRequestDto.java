package com.kakaobean.core.member.application.dto.request;

import lombok.Getter;

@Getter
public class ModifyMemberPasswordRequestDto {

    private Long memberId;
    private String nowPassword;
    private String passwordToChange;
    private String checkPasswordToChange;

    public ModifyMemberPasswordRequestDto(Long memberId, String nowPassword, String passwordToChange, String checkPasswordToChange) {
        this.memberId = memberId;
        this.nowPassword = nowPassword;
        this.passwordToChange = passwordToChange;
        this.checkPasswordToChange = checkPasswordToChange;
    }
}
