package com.kakaobean.core.member.application.dto.response;

import com.kakaobean.core.member.domain.Gender;
import com.kakaobean.core.member.domain.Member;
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

    public static FindMemberInfoResponseDto returnInfo(Member member){
        return new FindMemberInfoResponseDto(member.getName(), member.getAge(), member.getGender(), member.getAuth().getEmail(), member.getBirth());
    }
}
