package com.kakaobean.member;

import com.kakaobean.common.dto.CommandSuccessResponse;
import com.kakaobean.core.member.application.MemberProvider;
import com.kakaobean.core.member.application.MemberService;
import com.kakaobean.core.member.application.dto.response.FindEmailResponseDto;
import com.kakaobean.core.member.application.dto.response.FindMemberInfoResponseDto;
import com.kakaobean.core.member.application.dto.response.RegisterMemberResponseDto;
import com.kakaobean.core.member.domain.MemberRepository;
import com.kakaobean.member.dto.FindEmailRequest;
import com.kakaobean.member.dto.RegisterMemberRequest;

import com.kakaobean.member.dto.SendVerifiedEmailRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final MemberProvider memberProvider;

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
        FindEmailResponseDto res = memberProvider.findEmailByBirthAndName(request.getName(), request.getBirth());
        return new ResponseEntity(res, OK);
    }

    @GetMapping("/members/info")
    public ResponseEntity findMemberInfo(@AuthenticationPrincipal Long memberId) {
        FindMemberInfoResponseDto res = memberProvider.findMemberInfoByMemberId(memberId);
        return new ResponseEntity(res, OK);
    }


//    @GetMapping
//    public String test(@AuthenticationPrincipal Long id){
//        return String.valueOf(id);
//    }
}
