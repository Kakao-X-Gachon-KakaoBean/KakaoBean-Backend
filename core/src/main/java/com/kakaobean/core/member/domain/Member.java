package com.kakaobean.core.member.domain;

import com.kakaobean.core.common.domain.BaseEntity;
import com.kakaobean.core.common.domain.BaseStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer age;

    @Embedded
    private Auth auth;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;


    public Member(
            String name, LocalDate birth, String email, Role role, Gender gender, Integer age, String password,
            AuthProvider authProvider
    ) {
        super(BaseStatus.ACTIVE);
        this.name = name;
        this.birth = birth;
        this.auth = new Auth(email, password);
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.authProvider = authProvider;
    }
}
