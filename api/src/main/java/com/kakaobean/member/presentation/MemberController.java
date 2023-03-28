package com.kakaobean.member.presentation;

import com.kakaobean.core.member.service.MemberService;
import com.kakaobean.core.member.service.dto.response.RegisterMemberResponseDto;
import com.kakaobean.member.presentation.dto.RegisterMemberRequest;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/members")
    public ResponseEntity<RegisterMemberResponseDto> registerMember(@Validated @RequestBody RegisterMemberRequest request){
        RegisterMemberResponseDto res = memberService.registerMember(request.toServiceDto(passwordEncoder));
        return new ResponseEntity(res, OK);
    }
}
