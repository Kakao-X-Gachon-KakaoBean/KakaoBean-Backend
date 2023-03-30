package com.kakaobean.core.integration.member;

import com.kakaobean.core.exception.member.AlreadyExistsEmailException;
import com.kakaobean.core.factory.member.RegisterMemberServiceDtoFactory;
import com.kakaobean.core.integration.IntegrationTest;
import com.kakaobean.core.member.domain.Gender;
import com.kakaobean.core.member.domain.MemberRepository;
import com.kakaobean.core.member.service.MemberService;
import com.kakaobean.core.member.service.dto.request.RegisterMemberRequestDto;
import com.kakaobean.core.member.service.dto.response.RegisterMemberResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

public class MemberServiceIntegrationTest extends IntegrationTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @DisplayName("멤버를 등록한다.")
    @Test
    void registerMember(){
        //given
        RegisterMemberRequestDto dto = RegisterMemberServiceDtoFactory.createSuccessCaseRequestDto();

        //when
        RegisterMemberResponseDto res = memberService.registerMember(dto);

        //then
        assertThat(res.getMemberId()).isNotNull();
    }

    @DisplayName("이미 등록된 이메일이면 멤버를 등록할 수 없다.")
    @Test
    void failRegisterMember(){
        //given

        RegisterMemberRequestDto dto = RegisterMemberServiceDtoFactory.createSuccessCaseRequestDto();
        memberRepository.save(dto.toEntity());

        //when, then
        assertThatThrownBy(() -> {
            memberService.registerMember(dto);
        })
                .isInstanceOf(AlreadyExistsEmailException.class)
                .hasMessage("이미 존재하는 유저의 이메일입니다.");
    }

}
