package com.kakaobean.core.member.domain;

import com.kakaobean.core.member.exception.member.AlreadyExistsEmailException;
import com.kakaobean.core.member.application.dto.request.RegisterMemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberValidator {

    private final MemberRepository memberRepository;

    public void validate(RegisterMemberRequestDto dto) {

        /**
         * TODO: 레디스에서 이메일 검증하는 로직이 추가되어야 함.
         */

        if(memberRepository.findMemberByEmail(dto.getEmail()).isPresent()){
            throw new AlreadyExistsEmailException();
        }

    }
}
