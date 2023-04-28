package com.kakaobean.core.unit.application.member;

import com.kakaobean.core.member.domain.email.Email;
import com.kakaobean.core.member.domain.email.EmailRepository;
import com.kakaobean.core.member.domain.email.MemberVerifiedEmailService;
import com.kakaobean.core.member.exception.member.AlreadyExistsEmailException;
import com.kakaobean.core.factory.member.MemberFactory;
import com.kakaobean.core.factory.member.RegisterMemberServiceDtoFactory;
import com.kakaobean.core.member.domain.Member;
import com.kakaobean.core.member.domain.MemberRepository;
import com.kakaobean.core.member.domain.MemberValidator;
import com.kakaobean.core.member.application.MemberService;
import com.kakaobean.core.member.application.dto.request.RegisterMemberRequestDto;
import com.kakaobean.core.member.application.dto.response.RegisterMemberResponseDto;
import com.kakaobean.core.member.exception.member.NotExistsEmailException;
import com.kakaobean.core.member.exception.member.WrongEmailAuthKeyException;
import com.kakaobean.core.member.infrastructure.MemberVerifiedEmailServiceImpl;
import com.kakaobean.core.unit.UnitTest;
import com.kakaobean.independentlysystem.email.EmailSender;
import org.assertj.core.api.AbstractThrowableAssert;
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

    MemberVerifiedEmailService memberVerifiedEmailService;

    @Mock
    MemberRepository memberRepository;

    @Mock
    EmailSender emailSender;

    @Mock
    EmailRepository emailRepository;


    @BeforeEach
    void beforeEach(){
        memberVerifiedEmailService = new MemberVerifiedEmailServiceImpl(emailSender, emailRepository);
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

    @DisplayName("인증 이메일을 성공적으로 보낼 수 있다.")
    @Test
    void sendVerifiedEmail(){

        //given
        given(emailSender.sendVerificationEmail(Mockito.any(String.class))).willReturn("113336");

        //when, then
        memberService.sendVerificationEmail("example@gmail.com");
    }


    @DisplayName("재인증 이메일을 성공적으로 보낼 수 있고, 새로운 인증 키도 레디스에 저장된다.")
    @Test
    void sendVerifiedEmailAgain(){
        //given
        String email = "example@gmail.com";
        String authKey = "113336";
        given(emailSender.sendVerificationEmail(Mockito.any(String.class))).willReturn(authKey);
        given(emailRepository.hasKey(Mockito.any(Email.class))).willReturn(true);

        //when
        memberService.sendVerificationEmail(email);
    }

    @DisplayName("레디스에 저장된 이메일과는 다른 이메일로 회원 가입을 진행한다.")
    @Test
    void failRegisterMemberCase2(){
        //given
        RegisterMemberRequestDto dto = RegisterMemberServiceDtoFactory.createSuccessCaseRequestDto();
        Member member = MemberFactory.create();
        given(emailRepository.hasKey(Mockito.any(Email.class))).willReturn(false);

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            memberService.registerMember(dto);
        });

        //then
        result.isInstanceOf(NotExistsEmailException.class);
        result.hasMessage("존재하지 않는 유저의 이메일입니다.");

    }


    @DisplayName("이메일로 온 인증번호와는 다른 번호로 회원 가입을 진행한다.")
    @Test
    void failRegisterMemberCase3(){
        //given
        RegisterMemberRequestDto dto = RegisterMemberServiceDtoFactory.createSuccessCaseRequestDto();
        given(emailRepository.hasKey(Mockito.any(Email.class))).willReturn(true);
        given(emailRepository.getEmailCertification(Mockito.any(Email.class)))
                .willReturn(new Email(dto.getEmail(), "000000"));

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            memberService.registerMember(dto);
        });

        //then
        result.isInstanceOf(WrongEmailAuthKeyException.class);
        result.hasMessage("이메일 인증번호가 틀립니다.");
    }
}
