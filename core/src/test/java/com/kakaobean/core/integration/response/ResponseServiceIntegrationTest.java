package com.kakaobean.core.integration.response;

import com.kakaobean.core.factory.member.RegisterMemberServiceDtoFactory;
import com.kakaobean.core.integration.IntegrationTest;
import com.kakaobean.core.member.application.MemberService;
import com.kakaobean.core.member.application.dto.request.RegisterMemberRequestDto;
import com.kakaobean.core.member.application.dto.response.RegisterMemberResponseDto;
import com.kakaobean.core.member.domain.Email;
import com.kakaobean.core.member.domain.repository.EmailRepository;
import com.kakaobean.core.member.domain.repository.MemberRepository;
import com.kakaobean.core.response.application.ResponseProvider;
import com.kakaobean.core.response.application.ResponseService;
import com.kakaobean.core.response.application.dto.request.RegisterSurveyResponseRequestDto;
import com.kakaobean.core.response.application.dto.response.FindSurveyStatisticsResponseDto;
import com.kakaobean.core.response.application.dto.response.RegisterSurveyResponseSubmmitDto;
import com.kakaobean.core.survey.application.SurveyService;
import com.kakaobean.core.survey.application.dto.request.RegisterSurveyRequestDto;
import com.kakaobean.core.survey.application.dto.response.RegisterSurveyResponseDto;
import com.kakaobean.core.survey.domain.Survey;
import com.kakaobean.core.survey.domain.SurveyRepository;
import com.kakaobean.core.survey.exception.NotExistsSurveyException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import static com.kakaobean.core.factory.response.RegisterSurveyResponseServiceDtoFactory.*;
import static com.kakaobean.core.factory.survey.RegisterSurveyServiceDtoFactory.createSuccessCase1Request;
import static org.assertj.core.api.Assertions.*;

public class ResponseServiceIntegrationTest extends IntegrationTest {

    @Autowired
    ResponseService responseService;

    @Autowired
    ResponseProvider responseProvider;

    @Autowired
    SurveyService surveyService;

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;


    @DisplayName("설문조사에 대한 응답 제출에 성공한다.")
    @Test
    void registerSurveyResponse(){

        // given
        RegisterSurveyRequestDto testSurveyDto = createSuccessCase1Request();
        RegisterSurveyResponseDto testSurvey = surveyService.registerSurvey(testSurveyDto);
        RegisterSurveyResponseRequestDto dto = createSuccessSurveyResponseCase1Request(testSurvey.getSurveyId());

        // when
        RegisterSurveyResponseSubmmitDto result = responseService.registerSurveyResponse(dto);

        // then
        assertThat(result.getSurveyResponseId()).isNotNull();

    }

    @DisplayName("설문 결과 통계 조회에 성공한다.")
    @Test
    void findSurveyStatisticsTest(){

        // given
        RegisterMemberRequestDto testMemberDto = RegisterMemberServiceDtoFactory.createSuccessCaseRequestDto();
        emailRepository.save(new Email(testMemberDto.getEmail(), testMemberDto.getEmailAuthKey()));
        RegisterMemberResponseDto testMember = memberService.registerMember(testMemberDto); // 테스트 멤버 생성

        RegisterSurveyRequestDto testSurveyDto = createSuccessCase1Request(testMember.getMemberId());
        RegisterSurveyResponseDto testSurvey = surveyService.registerSurvey(testSurveyDto); // 테스트 설문 생성

        Survey mySurvey = surveyRepository.findSurveyBySurveyId(testSurvey.getSurveyId())
                .orElseThrow(NotExistsSurveyException::new);

        RegisterSurveyResponseRequestDto testResponseDto = createSuccessSurveyResponseCase2Request(mySurvey);
        RegisterSurveyResponseSubmmitDto testResponse = responseService.registerSurveyResponse(testResponseDto); // 테스트 응답 생성

        // when
        FindSurveyStatisticsResponseDto result = responseProvider.findSurveyStatistics(testSurveyDto.getMemberId(),testSurvey.getSurveyId());

        // then
        assertThat(result.getNumberOfResponse()).isEqualTo(1);
        assertThat(result.getSurveyId()).isEqualTo(result.getSurveyId());
    }

}
