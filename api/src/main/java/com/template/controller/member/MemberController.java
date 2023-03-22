package com.template.controller.member;

import com.template.core.service.member.MemberService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Repeatable;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/members")
    public ResponseEntity registerMember(
            @RequestBody @Validated RegisterMemberReq request
    ){
        memberService.registerMember(request.toServiceDto(passwordEncoder));
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @GetMapping("/members")
    public ResponseEntity seeMember(
            @AuthenticationPrincipal Long id)
    {
        System.out.println("id = " + id);
        return new ResponseEntity("success", HttpStatus.OK);
    }
}
