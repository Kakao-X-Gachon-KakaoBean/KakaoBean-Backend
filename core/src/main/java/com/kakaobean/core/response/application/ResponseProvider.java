package com.kakaobean.core.response.application;

import com.kakaobean.core.response.application.dto.response.FindResponsesDto;
import com.kakaobean.core.response.application.dto.response.SurveyResponseDto;
import com.kakaobean.core.response.infrastructure.ResponseQueryRepository;
import com.kakaobean.core.survey.application.SurveyProvider;
import com.kakaobean.core.survey.application.dto.response.FindSurveyResponseDto;
import com.kakaobean.core.survey.domain.SurveyRepository;
import com.kakaobean.core.survey.exception.NotExistsSurveyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResponseProvider {

    private final SurveyRepository surveyRepository;
    private final SurveyProvider surveyProvider;
    private final ResponseQueryRepository responseQueryRepository;


    public FindResponsesDto findResponses(Long memberId, Long surveyId){

        log.info("설문에 관련된 응답 조회 시작");

        //설문 주인만 조회할 수 있다.
        surveyRepository.findSurveyBySurveyIdAndOwnerId(surveyId, memberId).orElseThrow(NotExistsSurveyException::new);

        //조회
        FindSurveyResponseDto surveyDto = surveyProvider.getSurvey(surveyId);
        List<SurveyResponseDto> responsesDto = responseQueryRepository.findResponses(surveyId);
        log.info("설문에 관련된 응답 조회 끝");
        return new FindResponsesDto(surveyDto, responsesDto);
    }
}
