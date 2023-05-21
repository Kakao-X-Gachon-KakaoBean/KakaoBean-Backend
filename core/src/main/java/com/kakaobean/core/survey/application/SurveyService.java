package com.kakaobean.core.survey.application;

import com.kakaobean.core.response.application.ResponseService;
import com.kakaobean.core.response.domain.SurveyResponse;
import com.kakaobean.core.response.domain.SurveyResponseRepository;
import com.kakaobean.core.survey.application.dto.request.RegisterSurveyRequestDto;
import com.kakaobean.core.survey.application.dto.response.RegisterSurveyResponseDto;
import com.kakaobean.core.survey.application.dto.response.RemoveSurveyResponseDto;
import com.kakaobean.core.survey.domain.Survey;
import com.kakaobean.core.survey.domain.SurveyRepository;
import com.kakaobean.core.survey.domain.SurveyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final SurveyResponseRepository surveyResponseRepository;
    private final SurveyMapper surveyMapper;
    private final SurveyValidator surveyValidator;

    @Transactional
    public RegisterSurveyResponseDto registerSurvey(RegisterSurveyRequestDto dto){
        Survey survey = surveyMapper.mapFrom(dto);
        survey.place(surveyValidator);
        Survey saveSurvey = surveyRepository.save(survey);
        return new RegisterSurveyResponseDto(saveSurvey.getId());
    }

    @Transactional
    public RemoveSurveyResponseDto removeSurvey(Long surveyId){
        surveyRepository.deleteById(surveyId); // 여기서 응답

        // 응답 중 해당 서베이 아이디를 가지고 있는 데이터 삭제해야함
        List<SurveyResponse> findSurveyResponses = surveyResponseRepository.findSurveyResponseBySurveyId(surveyId);
        findSurveyResponses.forEach(findSurveyResponse -> surveyResponseRepository.deleteById(findSurveyResponse.getId()));

        // 이거 트렌젝션 보장 어케할지 생각
        // 이렇게는 왜 안되는거지
        //surveyResponseRepository.deleteAllBySurveyId(surveyId);

        return new RemoveSurveyResponseDto("해당 설문이 삭제되었습니다.");
    }
}
