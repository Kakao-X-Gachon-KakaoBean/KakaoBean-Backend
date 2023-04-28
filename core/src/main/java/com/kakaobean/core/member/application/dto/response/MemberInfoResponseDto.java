package com.kakaobean.core.member.application.dto.response;

import com.kakaobean.core.member.domain.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class MemberInfoResponseDto {
    private String name;
    private Integer age;
    private Gender gender;
    private String email;
    private LocalDate birth;

    public MemberInfoResponseDto(String name, Integer age, Gender gender, String email, LocalDate birth){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.birth = birth;
    }
}
