package com.kakaobean.core.unit.member.application;

import com.kakaobean.core.member.domain.email.Email;
import com.kakaobean.core.member.domain.email.EmailRepository;
import com.kakaobean.core.member.exception.member.AlreadyExistsEmailException;
import com.kakaobean.core.factory.member.MemberFactory;
import com.kakaobean.core.factory.member.RegisterMemberServiceDtoFactory;
import com.kakaobean.core.member.domain.Member;
import com.kakaobean.core.member.domain.MemberRepository;
import com.kakaobean.core.member.domain.MemberValidator;
import com.kakaobean.core.member.application.MemberService;
import com.kakaobean.core.member.application.dto.request.RegisterMemberRequestDto;
import com.kakaobean.core.member.application.dto.response.RegisterMemberResponseDto;
import com.kakaobean.core.member.infrastructure.MemberVerifiedEmailServiceImpl;
import com.kakaobean.core.unit.UnitTest;
import com.kakaobean.independentlysystem.email.EmailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

public class MemberServiceTest extends UnitTest {

    MemberService memberService;

    @Mock
    MemberRepository memberRepository;

    @Mock
    EmailSender emailSender;

    @Mock
    EmailRepository emailRepository;

    @BeforeEach
    void beforeEach(){
        MemberVerifiedEmailServiceImpl memberVerifiedEmailService = new MemberVerifiedEmailServiceImpl(emailSender, emailRepository);
        memberService = new MemberService(memberRepository, new MemberValidator(memberRepository), memberVerifiedEmailService);
    }

    @DisplayName("멤버를 성공적으로 등록한다.")
    @Test
    void successRegisterMember(){
        //given
        RegisterMemberRequestDto req = RegisterMemberServiceDtoFactory.createSuccessCaseRequestDto();
        Member member = MemberFactory.create();
        given(memberRepository.save(Mockito.any())).willReturn(member);
        given(emailRepository.hasKey(Mockito.any())).willReturn(true);
        given(emailRepository.getEmailCertification(Mockito.any())).willReturn(new Email(req.getEmail(), req.getEmailAuthKey()));

        //when
        RegisterMemberResponseDto res = memberService.registerMember(req);

        //then
        assertThat(res.getMemberId()).isEqualTo(1L);
        verify(memberRepository, times(1)).save(Mockito.any());
    }

    @DisplayName("이미 등록된 이메일이면 멤버를 등록할 수 없다.")
    @Test
    void failRegisterMemberCase1() {

        //given
        RegisterMemberRequestDto req = RegisterMemberServiceDtoFactory.createSuccessCaseRequestDto();
        Member member = MemberFactory.create();

        given(memberRepository.findMemberByEmail(Mockito.any())).willReturn(Optional.of(MemberFactory.create()));

        //when, then
        assertThatThrownBy(() -> {
                    memberService.registerMember(req);
                })
                .isInstanceOf(AlreadyExistsEmailException.class)
                .hasMessage("이미 존재하는 유저의 이메일입니다.");

        verify(memberRepository, times(1)).findMemberByEmail(Mockito.any());
    }
}
