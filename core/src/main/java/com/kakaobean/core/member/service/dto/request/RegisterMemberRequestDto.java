package com.kakaobean.core.member.service.dto.request;

import com.kakaobean.core.member.domain.AuthProvider;
import com.kakaobean.core.member.domain.Gender;
import com.kakaobean.core.member.domain.Member;
import com.kakaobean.core.member.domain.Role;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RegisterMemberRequestDto {

    private final String name;
    private final Integer age;
    private final Gender gender;
    private final String email;
    private final String password;
    private final LocalDate birth;

    @Builder
    public RegisterMemberRequestDto(
            String name, Integer age, Gender gender, String email, String password, LocalDate birth
    ) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.birth = birth;
    }

    public Member toEntity(){
        return new Member(
                name,
                birth,
                email,
                Role.ROLE_USER,
                gender,
                age,
                password,
                AuthProvider.local
        );
    }
}
