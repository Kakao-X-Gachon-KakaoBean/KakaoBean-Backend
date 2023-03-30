package com.kakaobean.core.unit.member.domain;

import com.kakaobean.core.exception.member.AlreadyExistsEmailException;
import com.kakaobean.core.factory.member.MemberFactory;
import com.kakaobean.core.factory.member.RegisterMemberServiceDtoFactory;
import com.kakaobean.core.member.domain.MemberRepository;
import com.kakaobean.core.member.domain.MemberValidator;
import com.kakaobean.core.member.service.dto.request.RegisterMemberRequestDto;
import com.kakaobean.core.unit.UnitTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;


import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;


public class MemberValidatorTest extends UnitTest {

    MemberValidator memberValidator;

    @Mock
    MemberRepository memberRepository;

    @BeforeEach
    void beforeEach(){
        memberValidator = new MemberValidator(memberRepository);
    }

    @DisplayName("사전에 동일한 이메일을 가진 멤버가 있다면 예외가 발생한다.")
    @Test()
    void failValidationWhenSomeoneHasSameEmail(){

        //given
        RegisterMemberRequestDto req = RegisterMemberServiceDtoFactory.createSuccessCaseRequestDto();
        given(memberRepository.findMemberByEmail(Mockito.any())).willReturn(Optional.of(MemberFactory.create()));

        //then, when
        Assertions.assertThatThrownBy(() -> {
            memberValidator.validate(req);
        })
                .isInstanceOf(AlreadyExistsEmailException.class)
                .hasMessage("이미 존재하는 유저의 이메일입니다.");

        verify(memberRepository, times(1)).findMemberByEmail(Mockito.any());
    }
}
