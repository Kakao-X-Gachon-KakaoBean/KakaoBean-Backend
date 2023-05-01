package com.kakaobean.core.integration.member;

import com.kakaobean.core.factory.member.MemberFactory;
import com.kakaobean.core.member.application.MemberProvider;
import com.kakaobean.core.member.application.dto.response.FindEmailResponseDto;
import com.kakaobean.core.member.application.dto.response.FindMemberInfoResponseDto;
import com.kakaobean.core.member.domain.*;
import com.kakaobean.core.member.domain.email.Email;
import com.kakaobean.core.member.domain.email.EmailRepository;
import com.kakaobean.core.member.exception.member.AlreadyExistsEmailException;
import com.kakaobean.core.factory.member.RegisterMemberServiceDtoFactory;
import com.kakaobean.core.integration.IntegrationTest;
import com.kakaobean.core.member.application.MemberService;
import com.kakaobean.core.member.application.dto.request.RegisterMemberRequestDto;
import com.kakaobean.core.member.application.dto.response.RegisterMemberResponseDto;
import com.kakaobean.core.member.exception.member.NotExistsEmailException;
import com.kakaobean.core.member.exception.member.NotExistsMembersInfoException;
import com.kakaobean.core.member.exception.member.WrongEmailAuthKeyException;
import com.kakaobean.independentlysystem.email.EmailSender;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailSender;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.BDDMockito.given;

public class MemberServiceIntegrationTest extends IntegrationTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EmailRepository emailRepository;

    @MockBean
    EmailSender mailSender;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    MemberProvider memberProvider;

    @BeforeEach
    void beforeEach(){
        redisTemplate.execute((RedisCallback<Void>) connection -> {
            connection.flushDb();
            return null;
        });    }

    @DisplayName("멤버를 등록한다.")
    @Test
    void registerMember(){
        //given
        RegisterMemberRequestDto dto = RegisterMemberServiceDtoFactory.createSuccessCaseRequestDto();
        emailRepository.save(new Email(dto.getEmail(), dto.getEmailAuthKey()));

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

    @DisplayName("인증 이메일을 성공적으로 보낼 수 있고, 인증 값은 레디스에 저장된다.")
    @Test
    void sendVerifiedEmail(){
        //given
        String email = "example@gmail.com";
        String authKey = "113336";
        given(mailSender.sendVerificationEmail(Mockito.any(String.class))).willReturn(authKey);

        //when
        memberService.sendVerificationEmail(email);

        //then
        Email result = emailRepository.getEmailCertification(new Email(email, authKey));
        assertThat(result.getEmail()).isEqualTo(email);
        assertThat(result.getAuthKey()).isEqualTo(authKey);
    }

    @DisplayName("재인증 이메일을 성공적으로 보낼 수 있고, 새로운 인증 키도 레디스에 저장된다.")
    @Test
    void sendVerifiedEmailAgain(){
        //given
        String email = "example@gmail.com";
        String authKey = "113336";
        String authKey2 = "112233";
        given(mailSender.sendVerificationEmail(Mockito.any(String.class))).willReturn(authKey, authKey2);
        memberService.sendVerificationEmail(email);

        //when
        memberService.sendVerificationEmail(email);

        //then
        Email result = emailRepository.getEmailCertification(new Email(email, authKey2));
        assertThat(result.getEmail()).isEqualTo(email);
        assertThat(result.getAuthKey()).isEqualTo(authKey2);
    }

    @DisplayName("레디스에 저장된 이메일과는 다른 이메일로 회원 가입을 진행한다.")
    @Test
    void failRegisterMemberCase1(){
        //given
        RegisterMemberRequestDto dto = RegisterMemberServiceDtoFactory.createSuccessCaseRequestDto();
        given(mailSender.sendVerificationEmail(Mockito.any(String.class))).willReturn(dto.getEmailAuthKey());
        memberService.sendVerificationEmail("123@gmail.com");

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
    void failRegisterMemberCase2(){
        //given
        RegisterMemberRequestDto dto = RegisterMemberServiceDtoFactory.createSuccessCaseRequestDto();
        given(mailSender.sendVerificationEmail(Mockito.any(String.class))).willReturn("000000");
        memberService.sendVerificationEmail(dto.getEmail());

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            memberService.registerMember(dto);
        });

        //then
        result.isInstanceOf(WrongEmailAuthKeyException.class);
        result.hasMessage("이메일 인증번호가 틀립니다.");
    }

    @DisplayName("이메일을 찾을 수 있어야 한다.")
    @Test
    void findEmail(){
        //given
        String name = "bean";
        String email = "123@gmail.com";
        LocalDate birth = LocalDate.of(1999, 6, 27);
        Member member = Member.builder()
                .name(name)
                .auth(new Auth(email, "pwd"))
                .birth(birth)
                .build();
        memberRepository.save(member);

        //when
        FindEmailResponseDto res = memberProvider.findEmailByBirthAndName(name, birth);

        //then
        Assertions.assertThat(res.getEmail()).isEqualTo(email);

    }

    @DisplayName("멤버 정보를 찾을 수 있어야 한다.")
    @Test
    void findMemberInfo() {
        //given
        Member member = memberRepository.save(MemberFactory.create());

        //when
        FindMemberInfoResponseDto res = memberProvider.findMemberInfoByMemberId(member.getId());

        //then
        assertThat(res.getName()).isEqualTo(member.getName());
        assertThat(res.getAge()).isEqualTo(member.getAge());
        assertThat(res.getGender()).isEqualTo(member.getGender());
        assertThat(res.getEmail()).isEqualTo(member.getAuth().getEmail());
        assertThat(res.getBirth()).isEqualTo(member.getBirth());
    }

    @DisplayName("저장된 멤버 ID와는 다른 ID로 멤버 정보를 호출한다.")
    @Test
    void findMemberInfoException() {
        //given
        Member member = MemberFactory.create();
        memberRepository.save(member);

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            memberProvider.findMemberInfoByMemberId(member.getId());
        });

        //then
        result.isInstanceOf(NotExistsMembersInfoException.class);
        result.hasMessage("멤버 정보가 없습니다.");
    }
}
