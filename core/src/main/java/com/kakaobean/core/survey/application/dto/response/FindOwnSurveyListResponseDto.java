package com.kakaobean.core.survey.application.dto.response;

import com.kakaobean.core.survey.domain.Survey;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class FindOwnSurveyListResponseDto {

    private List<FindOwnSurveyResponseDto> myOwnSurveys;

    public FindOwnSurveyListResponseDto(List<Survey> myOwnSurveyList,
                                        List<Integer> numberOfResponseEachSurveys) {
        this.myOwnSurveys = createDto(myOwnSurveyList, numberOfResponseEachSurveys);

    }

    public static FindOwnSurveyListResponseDto from(List<Survey> myOwnSurveyList,
                                                    List<Integer> numberOfResponseEachSurveys){
        return new FindOwnSurveyListResponseDto(myOwnSurveyList, numberOfResponseEachSurveys);
    }

    public List<FindOwnSurveyResponseDto> createDto(List<Survey> myOwnSurveyList,
                                               List<Integer> numberOfResponseEachSurveys){

        List<FindOwnSurveyResponseDto> tempDto = new ArrayList<>();

        for (int i = 0; i<myOwnSurveyList.size(); i++){
            tempDto.add(
                    new FindOwnSurveyResponseDto(
                            myOwnSurveyList.get(i).getId(),
                            myOwnSurveyList.get(i).getTitle(),
                            numberOfResponseEachSurveys.get(i)
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
