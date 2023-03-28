package com.kakaobean.member.dto;

import com.kakaobean.core.member.domain.Gender;
import com.kakaobean.core.member.service.dto.request.RegisterMemberRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */

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

    public RegisterMemberRequestDto toServiceDto(PasswordEncoder passwordEncoder){
        if(!password.equals(checkPassword)){
            throw new RuntimeException("비밀번호가 다릅니다");
        }
        return new RegisterMemberRequestDto(
                name,
                age,
                gender,
                email,
                passwordEncoder.encode(password),
                birth
        );
    }
}
