package com.kakaobean.member;

import com.kakaobean.common.dto.CommandSuccessResponse;
import com.kakaobean.core.member.application.MemberService;
import com.kakaobean.core.member.application.dto.response.FindEmailResponseDto;
import com.kakaobean.core.member.application.dto.response.RegisterMemberResponseDto;
import com.kakaobean.member.dto.FindEmailRequest;
import com.kakaobean.member.dto.RegisterMemberRequest;

import com.kakaobean.member.dto.SendVerifiedEmailRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
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

    @PostMapping("/emails")
    public ResponseEntity sendVerificationEmail(@RequestBody @Validated SendVerifiedEmailRequest request){
        memberService.sendVerificationEmail(request.getEmail());
        return new ResponseEntity(new CommandSuccessResponse(), OK);
    }

    @PostMapping("/members/find-email")
    public ResponseEntity findEmail(@RequestBody @Validated FindEmailRequest request){
        FindEmailResponseDto res = memberService.findEmailByBirthAndName(request.getName(), request.getBirth());
        return new ResponseEntity(res, OK);
    }


//    @GetMapping
//    public String test(@AuthenticationPrincipal Long id){
//        return String.valueOf(id);
//    }
}
