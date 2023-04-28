package com.kakaobean.unit.controller.factory.member;

import com.kakaobean.core.member.domain.Gender;
import com.kakaobean.core.member.application.dto.response.RegisterMemberResponseDto;
import com.kakaobean.member.dto.RegisterMemberRequest;

import java.time.LocalDate;

public class RegisterMemberRequestFactory {

    private RegisterMemberRequestFactory(){}

    public static RegisterMemberRequest createRequest(){
        return RegisterMemberRequest.builder()
                .name("kakoBean")
                .age(25)
                .gender(Gender.MALE)
                .email("example@gmail.com")
                .password("1q2w3e4r!")
                .checkPassword("1q2w3e4r!")
                .birth(LocalDate.parse("1998-03-04"))
                .emailAuthKey("113336")
                .build();
    }

    public static RegisterMemberResponseDto createResponseDto(){
        return new RegisterMemberResponseDto(1L);
    }
}
