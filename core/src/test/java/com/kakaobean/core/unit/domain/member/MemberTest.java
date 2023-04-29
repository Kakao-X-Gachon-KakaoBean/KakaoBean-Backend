package com.kakaobean.core.unit.domain.member;

import com.kakaobean.core.factory.member.MemberFactory;
import com.kakaobean.core.factory.member.RegisterMemberServiceDtoFactory;
import com.kakaobean.core.member.application.dto.request.RegisterMemberRequestDto;
import com.kakaobean.core.member.domain.Member;
import com.kakaobean.core.member.domain.MemberRepository;
import com.kakaobean.core.member.domain.MemberValidator;
import com.kakaobean.core.member.domain.email.MemberVerifiedEmailService;
import com.kakaobean.core.member.exception.member.AlreadyExistsEmailException;
import com.kakaobean.core.unit.UnitTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MemberTest extends UnitTest {

    MemberValidator memberValidator;

    @Mock
    MemberRepository memberRepository;

    @Mock
    MemberVerifiedEmailService memberVerifiedEmailService;

    @BeforeEach
    void beforeEach(){
        memberValidator = new MemberValidator(memberRepository);
    }

    @DisplayName("사전에 동일한 이메일을 가진 멤버가 있다면 예외가 발생한다.")
    @Test
    void failValidationWhenSomeoneHasSameEmail(){

        //given
        Member member = MemberFactory.create();
        given(memberRepository.findMemberByEmail(Mockito.any())).willReturn(Optional.of(MemberFactory.create()));

        //then, when
        Assertions.assertThatThrownBy(() -> {
                    member.place(memberValidator, memberVerifiedEmailService, "");
                })
                .isInstanceOf(AlreadyExistsEmailException.class)
                .hasMessage("이미 존재하는 유저의 이메일입니다.");

        verify(memberRepository, times(1)).findMemberByEmail(Mockito.any());
    }


    @DisplayName("사전에 동일한 이메일을 가진 멤버가 있다면 예외가 발생한다.")
    @Test
    void successValidation(){

        //given
        Member member = MemberFactory.create();
        given(memberRepository.findMemberByEmail(Mockito.any())).willReturn(Optional.ofNullable(null));

        //when
        member.place(memberValidator, memberVerifiedEmailService, "");

        //then
        verify(memberRepository, times(1)).findMemberByEmail(Mockito.any());
    }
}
