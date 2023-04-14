package com.kakaobean.core.member.application;


import com.kakaobean.core.member.domain.MemberRepository;
import com.kakaobean.core.member.domain.Member;
import com.kakaobean.core.member.domain.MemberValidator;
import com.kakaobean.core.member.application.dto.request.RegisterMemberRequestDto;
import com.kakaobean.core.member.application.dto.response.RegisterMemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;

    @Transactional(readOnly = false)
    public RegisterMemberResponseDto registerMember(
            RegisterMemberRequestDto dto
    ){
        memberValidator.validate(dto);
        Member member = dto.toEntity();
        Member savedMember = memberRepository.save(member);
        return new RegisterMemberResponseDto(savedMember.getId());
    }
}
