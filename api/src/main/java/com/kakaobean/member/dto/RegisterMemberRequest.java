package com.kakaobean.member.dto;

import com.kakaobean.core.member.domain.Gender;
import com.kakaobean.core.member.application.dto.request.RegisterMemberRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class RegisterMemberRequest {

    @NotBlank
    private String name;

    @Min(1)
    private Integer age;

    @NotNull
    private Gender gender;

    @Email
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String checkPassword;

    @NotNull
    private LocalDate birth;

    @NotBlank
    private String emailAuthKey;

    public RegisterMemberRequestDto toServiceDto(PasswordEncoder passwordEncoder){
        if(isSamePassword()){
            throw new RuntimeException("비밀번호가 다릅니다");
        }
        return new RegisterMemberRequestDto(
                name,
                age,
                gender,
                email,
                passwordEncoder.encode(password),
                birth,
                emailAuthKey
        );
    }

    private boolean isSamePassword() {
        return !password.equals(checkPassword);
    }

    @Builder
    public RegisterMemberRequest(String name,
                                 Integer age,
                                 Gender gender,
                                 String email,
                                 String password,
                                 String checkPassword,
                                 LocalDate birth,
                                 String emailAuthKey
    ) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.checkPassword = checkPassword;
        this.birth = birth;
        this.emailAuthKey = emailAuthKey;
    }
}
