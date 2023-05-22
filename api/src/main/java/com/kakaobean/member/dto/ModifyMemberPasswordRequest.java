package com.kakaobean.member.dto;

import com.kakaobean.core.member.application.dto.request.ModifyMemberPasswordRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifyMemberPasswordRequest {

    private String nowPassword;
    private String passwordToChange;
    private String checkPasswordToChange;

    public ModifyMemberPasswordRequest(String nowPassword, String passwordToChange, String checkPasswordToChange) {
        this.nowPassword = nowPassword;
        this.passwordToChange = passwordToChange;
        this.checkPasswordToChange = checkPasswordToChange;
    }

    public ModifyMemberPasswordRequestDto toServiceDto(Long memberId) {
        return new ModifyMemberPasswordRequestDto(memberId, nowPassword, passwordToChange, checkPasswordToChange);
    }
}
