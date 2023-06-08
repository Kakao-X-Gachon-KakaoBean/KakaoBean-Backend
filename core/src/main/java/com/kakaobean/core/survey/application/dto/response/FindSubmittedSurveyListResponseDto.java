package com.kakaobean.core.survey.application.dto.response;

import com.kakaobean.core.response.domain.SurveyResponse;
import com.kakaobean.core.survey.domain.Survey;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class FindSubmittedSurveyListResponseDto {

    private List<FindSubmittedSurveyResponseDto> mySubmittedSurveys;

    public FindSubmittedSurveyListResponseDto(List<SurveyResponse> mySurveyResponses,
                                              List<Survey> mySubmittedSurveys) {
        this.mySubmittedSurveys = createDto(mySurveyResponses, mySubmittedSurveys);
    }

    public static FindSubmittedSurveyListResponseDto from(List<SurveyResponse> mySurveyResponses,
                                                          List<Survey> mySubmittedSurveys){
        return new FindSubmittedSurveyListResponseDto(mySurveyResponses, mySubmittedSurveys);
    }

    public List<FindSubmittedSurveyResponseDto> createDto(List<SurveyResponse> mySurveyResponses,
                                                          List<Survey> mySubmittedSurveys){

        List<FindSubmittedSurveyResponseDto> tempDto = new ArrayList<>();

        for (int i = 0; i < mySurveyResponses.size(); i++) {
            tempDto.add(
                    new FindSubmittedSurveyResponseDto(
                            mySurveyResponses.get(i).getSurveyId(),
                            mySurveyResponses.get(i).getId(),
                            mySubmittedSurveys.get(i).getTitle(),
                            mySurveyResponses.get(i).getCreatedAt()
                    )
            );
        }
        return tempDto;
    }

    /**
     * 테스트용
     */
    @Builder
    public FindSubmittedSurveyListResponseDto(List<FindSubmittedSurveyResponseDto> mySubmittedSurveys) {
        this.mySubmittedSurveys = mySubmittedSurveys;
    }
}
