package com.kakaobean.core.member.service.dto.response;

import com.kakaobean.core.member.domain.AuthProvider;
import com.kakaobean.core.member.domain.Gender;
import com.kakaobean.core.member.domain.Member;
import com.kakaobean.core.member.domain.Role;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RegisterMemberResponseDto {

    private final Long memberId;

    public RegisterMemberResponseDto(Long memberId) {
        this.memberId = memberId;
    }
}
