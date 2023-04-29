package com.kakaobean.core.member.application.dto.response;

import com.kakaobean.core.member.domain.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class FindMemberInfoResponseDto {
    private String name;
    private Integer age;
    private Gender gender;
    private String email;
    private LocalDate birth;

    public FindMemberInfoResponseDto(String name, Integer age, Gender gender, String email, LocalDate birth){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.birth = birth;
    }
}
