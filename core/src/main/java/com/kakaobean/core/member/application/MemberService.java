package com.kakaobean.core.member.application;


import com.kakaobean.core.member.application.dto.response.FindEmailResponseDto;
import com.kakaobean.core.member.application.dto.response.MemberInfoResponseDto;
import com.kakaobean.core.member.domain.MemberRepository;
import com.kakaobean.core.member.domain.Member;
import com.kakaobean.core.member.domain.MemberValidator;
import com.kakaobean.core.member.application.dto.request.RegisterMemberRequestDto;
import com.kakaobean.core.member.application.dto.response.RegisterMemberResponseDto;
import com.kakaobean.core.member.domain.email.MemberVerifiedEmailService;
import com.kakaobean.core.member.exception.member.NotExistsMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;
    private final MemberVerifiedEmailService memberVerifiedEmailService;

    @Transactional(readOnly = false)
    public RegisterMemberResponseDto registerMember(RegisterMemberRequestDto dto){
        Member member = dto.toEntity();
        member.place(memberValidator, memberVerifiedEmailService, dto.getEmailAuthKey());
        Member savedMember = memberRepository.save(member);
        return new RegisterMemberResponseDto(savedMember.getId());
    }

    public void sendVerificationEmail(String email) {
        memberVerifiedEmailService.sendVerificationEmail(email);
    }

    public FindEmailResponseDto findEmailByBirthAndName(String name, LocalDate birth) {
        Member member = memberRepository.findMemberByNameAndBirth(name, birth)
                .orElseThrow(() -> new NotExistsMemberException());
        return new FindEmailResponseDto(member.getAuth().getEmail());
    }

    public MemberInfoResponseDto memberInfoByMemberId(Long memberId){
        Member member = memberRepository.findMemberById(memberId)
                .orElseThrow(() -> new NotExistsMemberException());
        return new MemberInfoResponseDto(member.getName(),member.getAge(),member.getGender(), member.getAuth().getEmail(), member.getBirth());
    }
}
