package com.kakaobean.core.survey.application.dto.response;

import com.kakaobean.core.survey.domain.CloseStatus;
import com.kakaobean.core.survey.domain.Survey;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class FindOwnSurveyListResponseDto {

    private List<FindOwnSurveyResponseDto> myOwnSurveys;

    public FindOwnSurveyListResponseDto(List<Survey> myOwnSurveyList,
                                        List<Integer> numberOfResponseEachSurvey) {
        this.myOwnSurveys = createDto(myOwnSurveyList, numberOfResponseEachSurvey);
    }

    public static FindOwnSurveyListResponseDto from(List<Survey> myOwnSurveyList,
                                                    List<Integer> numberOfResponseEachSurvey) {
        return new FindOwnSurveyListResponseDto(myOwnSurveyList, numberOfResponseEachSurvey);
    }

    /**
     * Dto 생성
     */
    public List<FindOwnSurveyResponseDto> createDto(List<Survey> myOwnSurveyList,
                                                    List<Integer> numberOfResponseEachSurvey) {

        List<FindOwnSurveyResponseDto> tempDto = new ArrayList<>();

        for (int i = 0; i < myOwnSurveyList.size(); i++) {

            Boolean closeStatus = myOwnSurveyList.get(i).getCloseStatus() == CloseStatus.ACTIVE ? true : false;

            tempDto.add(
                    new FindOwnSurveyResponseDto(
                            myOwnSurveyList.get(i).getId(),
                            myOwnSurveyList.get(i).getTitle(),
                            numberOfResponseEachSurvey.get(i),
                            closeStatus
                    )
            );
        }
        return tempDto;
    }

    /**
     * 테스트용
     */
    @Builder
    public FindOwnSurveyListResponseDto(List<FindOwnSurveyResponseDto> myOwnSurveys) {
        this.myOwnSurveys = myOwnSurveys;
    }
}
