package com.kakaobean.member;

import com.kakaobean.core.member.service.MemberService;
import com.kakaobean.core.member.service.dto.response.RegisterMemberResponseDto;
import com.kakaobean.member.dto.RegisterMemberRequest;

import com.kakaobean.security.UserPrincipal;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public String test(@AuthenticationPrincipal Long id){
        System.out.println("id = " + id.toString());
        return String.valueOf(id);
    }
}
