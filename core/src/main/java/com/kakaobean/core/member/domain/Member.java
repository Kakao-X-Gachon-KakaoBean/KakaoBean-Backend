package com.kakaobean.core.member.domain;

import com.kakaobean.core.common.domain.BaseEntity;
import com.kakaobean.core.common.domain.BaseStatus;
import com.kakaobean.core.member.domain.service.ModifyMemberService;
import com.kakaobean.core.member.domain.service.VerifiedEmailService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.net.PasswordAuthentication;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    /**
     * 프로덕션 생성자
     */
    public Member(String name,
                  LocalDate birth,
                  String email,
                  Role role,
                  Gender gender,
                  Integer age,
                  String password,
                  AuthProvider authProvider) {
        super(BaseStatus.ACTIVE);
        this.name = name;
        this.birth = birth;
        this.auth = new Auth(email, password);
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.authProvider = authProvider;
    }

    /**
     * 테스트 코드를 위한 생성자.
     */
    @Builder
    public Member(Long id,
                  String name,
                  Integer age,
                  Auth auth,
                  Role role,
                  Gender gender,
                  LocalDate birth,
                  AuthProvider authProvider
    ) {
        super(BaseStatus.ACTIVE);
        this.id = id;
        this.name = name;
        this.age = age;
        this.auth = auth;
        this.role = role;
        this.gender = gender;
        this.birth = birth;
        this.authProvider = authProvider;
    }

    public void validate(MemberValidator memberValidator) {
        memberValidator.validate(auth.getEmail());
    }

    public void verifyEmail(VerifiedEmailService memberVerifiedEmailService,
                      String authKey){
        memberVerifiedEmailService.verifyAuthEmailKey(auth.getEmail(), authKey);
    }

    public void modifyPassword(ModifyMemberService modifyMemberService,
                               String passwordToChange,
                               String checkPasswordToChange) {
        modifyMemberService.modifyPassword(this, passwordToChange, checkPasswordToChange);
    }

    public void updatePassword(String newPassword) {
        this.auth = new Auth(this.auth.getEmail(), newPassword);
    }
}
