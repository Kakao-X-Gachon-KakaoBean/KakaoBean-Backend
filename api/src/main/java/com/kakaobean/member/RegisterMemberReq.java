package com.kakaobean.member;

import com.kakaobean.core.member.service.RegisterMemberRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class RegisterMemberReq {

    @Email
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String checkPassword;

    public RegisterMemberRequestDto toServiceDto(PasswordEncoder passwordEncoder) {
        if(!password.equals(checkPassword)){
            throw new RuntimeException("두 비밀번호가 다릅니다.");
        }
        return new RegisterMemberRequestDto(email, passwordEncoder.encode(password));
    }

    /**
     * 테스트 코드를 위한 생성자
     */
    public RegisterMemberReq(String email, String password, String checkPassword) {
        this.email = email;
        this.password = password;
        this.checkPassword = checkPassword;
    }
}
