package com.kakaobean.core.member.domain;

import com.kakaobean.core.member.exception.member.AlreadyExistsEmailException;
import com.kakaobean.core.member.application.dto.request.RegisterMemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberValidator {

    private final MemberRepository memberRepository;

    public void validate(String email) {
        if(memberRepository.findMemberByEmail(email).isPresent()){
            throw new AlreadyExistsEmailException();
        }
    }
}
