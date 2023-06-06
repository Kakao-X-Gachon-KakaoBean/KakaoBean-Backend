package com.kakaobean.core.response.application;

import com.kakaobean.core.member.domain.Gender;
import com.kakaobean.core.member.domain.Member;
import com.kakaobean.core.member.domain.repository.MemberRepository;
import com.kakaobean.core.member.exception.member.NotExistsMemberException;
import com.kakaobean.core.response.application.dto.response.FindResponsesDto;
import com.kakaobean.core.response.application.dto.response.FindSurveyStatisticsResponseDto;
import com.kakaobean.core.response.application.dto.response.SurveyResponseDto;
import com.kakaobean.core.response.application.dto.response.question.QuestionResponseDto;

import com.kakaobean.core.response.domain.SurveyResponse;
import com.kakaobean.core.response.domain.SurveyResponseRepository;
import com.kakaobean.core.response.infrastructure.ResponseQueryRepository;
import com.kakaobean.core.survey.application.SurveyProvider;
import com.kakaobean.core.survey.application.dto.response.FindSurveyResponseDto;
import com.kakaobean.core.survey.domain.Survey;
import com.kakaobean.core.survey.application.dto.response.question.FindQuestionResponseDto;

import com.kakaobean.core.survey.domain.SurveyRepository;
import com.kakaobean.core.survey.exception.NotExistsSurveyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResponseProvider {

    private final SurveyResponseRepository surveyResponseRepository;
    private final SurveyRepository surveyRepository;
    private final SurveyProvider surveyProvider;
    private final ResponseQueryRepository responseQueryRepository;
    private final MemberRepository memberRepository;

    public FindResponsesDto findResponses(Long memberId, Long surveyId){

        log.info("설문에 관련된 응답 조회 시작");

        //설문 주인만 조회할 수 있다.
        surveyRepository.findSurveyBySurveyIdAndOwnerId(surveyId, memberId)
                .orElseThrow(NotExistsSurveyException::new);

        //조회
        FindSurveyResponseDto surveyDto = surveyProvider.getSurvey(surveyId, memberId);
        List<SurveyResponseDto> responsesDto = responseQueryRepository.findResponses(surveyId);

        setTitle(surveyDto, responsesDto);

        log.info("설문에 관련된 응답 조회 끝");
        return new FindResponsesDto(surveyDto, responsesDto);
    }


    public FindSurveyStatisticsResponseDto findSurveyStatistics(Long memberId, Long surveyId){
        //설문 주인만 조회할 수 있다.
        Survey mySurvey = surveyRepository.findSurveyBySurveyIdAndOwnerId(surveyId, memberId)
                .orElseThrow(NotExistsSurveyException::new);
        List<SurveyResponse> surveyResponses = surveyResponseRepository.findSurveyResponseBySurveyId(surveyId);
        Integer numberOfResponse = surveyResponses.size();
        List<Member> respondents = surveyResponses.stream()
                .map(surveyResponse -> memberRepository.findMemberById(surveyResponse.getRespondent().getMemberId())
                        .orElseThrow(NotExistsMemberException::new))
                .collect(Collectors.toList());
        List<SurveyResponseDto> allResponses = findResponses(memberId, surveyId).getResponses();

        return new FindSurveyStatisticsResponseDto(mySurvey, numberOfResponse, respondents, allResponses);

    }


    private void setTitle(FindSurveyResponseDto surveyDto, List<SurveyResponseDto> responsesDto) {
        for (int i = 0; i < responsesDto.size(); i++) {
            for (int j = 0; j < surveyDto.getQuestions().size(); j++) {
                SurveyResponseDto surveyResponseDto = responsesDto.get(i);

                FindQuestionResponseDto findQuestionResponseDto = surveyDto.getQuestions().get(j);
                QuestionResponseDto questionResponseDto = surveyResponseDto.getQuestionResponses().get(j);
                questionResponseDto.setTitle(findQuestionResponseDto.getTitle());
            }
        }
    }
}

