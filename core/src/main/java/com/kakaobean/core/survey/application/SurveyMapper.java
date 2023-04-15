package com.kakaobean.core.survey.application;

import com.kakaobean.core.survey.application.dto.RegisterSurveyRequestDto;
import com.kakaobean.core.survey.application.dto.question.RegisterMultipleChoiceQuestionRequestDto;
import com.kakaobean.core.survey.application.dto.question.RegisterQuestionRequestDto;
import com.kakaobean.core.survey.domain.Survey;
import com.kakaobean.core.survey.domain.SurveyOwner;
import com.kakaobean.core.survey.domain.question.Question;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SurveyMapper {

    public Survey mapFrom(RegisterSurveyRequestDto dto){
        Survey survey = new Survey(
                new SurveyOwner(dto.getMemberId()),
                createQuestion(dto)
        );

        initMultipleChoiceQuestionLogic(survey.getQuestions(), dto.getDtoList());
        return survey;
    }

    private List<Question> createQuestion(RegisterSurveyRequestDto dto) {
        return dto.getDtoList()
                .stream()
                .map(questionDto -> questionDto.toEntity())
                .collect(Collectors.toList());
    }

    private void initMultipleChoiceQuestionLogic(
            List<Question> questions,
            List<RegisterQuestionRequestDto> dtoList
    ) {
        /**
         * 1. 먼저 순회하면서 객관식 DTO을 찾는다.
         * 2. 찾는다면 Logic이 빈 값인지 찾는다.
         * 3. 빈 값이 아니라면 Logic을 생성한다.
         */

        System.out.println("start");
        for (int i = 0; i < dtoList.size(); i++) {
            RegisterQuestionRequestDto dto = dtoList.get(i);

            //답안에 따른 로직을 가진 질문(객관식)이 아니면 return;
            //현재 도메인 로직 상 객관식만 다음 로직을 가짐.
            if(!dto.hasQuestionFlowLogic()){
                continue;
            }

            //객관식 질문을 도출.
            RegisterMultipleChoiceQuestionRequestDto multipleChoiceDto = (RegisterMultipleChoiceQuestionRequestDto) dto;
            MultipleChoiceQuestion question = (MultipleChoiceQuestion )questions.get(i);

            multipleChoiceDto.initDetailQuestionFlowLogic(question, questions, question.getAnswers());
        }
    }
}
