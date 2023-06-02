package com.kakaobean.core.response.application.dto.response.statistics;

import com.kakaobean.core.response.application.dto.response.SurveyResponseDto;
import com.kakaobean.core.response.application.dto.response.question.EssayQuestionResponseDto;
import com.kakaobean.core.response.application.dto.response.question.QuestionResponseDto;
import com.kakaobean.core.survey.application.dto.QuestionDTOType;
import com.kakaobean.core.survey.domain.question.essay.EssayQuestion;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class EssayQuestionStatisticsDto extends QuestionStatisticsDto {

    private List<String> answers = new ArrayList<>();

    public EssayQuestionStatisticsDto(QuestionDTOType type,
                                      String title,
                                      String explanation,
                                      EssayQuestion essayQuestion,
                                      List<SurveyResponseDto> allResponses) {
        super(type, title, explanation);
        addAnswer(essayQuestion, allResponses);
    }

    public void addAnswer(EssayQuestion essayQuestion, List<SurveyResponseDto> allResponses){
        for(SurveyResponseDto surveyResponse : allResponses) {

            List<QuestionResponseDto> questionResponses = surveyResponse.getQuestionResponses();

            for (QuestionResponseDto questionResponse : questionResponses) {
                if (essayQuestion.getId() == questionResponse.getQuestionId()){
                    EssayQuestionResponseDto essayQuestionResponse = (EssayQuestionResponseDto)questionResponse;
                    this.answers.add(essayQuestionResponse.getAnswer());
                }
            }
        }
    }
}
