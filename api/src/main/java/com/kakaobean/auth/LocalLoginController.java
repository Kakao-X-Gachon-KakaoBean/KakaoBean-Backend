package com.kakaobean.auth;

import com.kakaobean.core.exception.member.NotExistsEmailException;
import com.kakaobean.core.member.domain.Member;
import com.kakaobean.core.member.domain.MemberRepository;
import com.kakaobean.auth.dto.LocalLoginResponse;
import com.kakaobean.auth.dto.LocalLoginRequest;
import com.kakaobean.security.TokenProvider;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LocalLoginController {

    private final AuthenticationManager authenticationManager;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody @Validated LocalLoginRequest request) {

        Member member = memberRepository.findMemberByEmail(request.getEmail())
                .orElseThrow(NotExistsEmailException::new);

        if(!passwordEncoder.matches(request.getPassword(), member.getAuth().getPassword())){
            throw new RuntimeException("비밀번호가 틀립니다.");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new LocalLoginResponse(token));
    }
}
