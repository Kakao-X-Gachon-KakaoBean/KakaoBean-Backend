package com.kakaobean.core.survey.domain;

import com.kakaobean.core.survey.domain.question.Question;
import com.kakaobean.core.survey.exception.SameQuestionNumberException;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SurveyValidator {

    public void validate(Survey survey){
        //TODO 설문을 생성할 때 검증 로직을 추가해야함.

        //질문 번호가 겹치는게 없어야한다.
        checkExistSameQuestionNumber(survey);

        //TODO 객관식에서 분기가 존재한다면 겹치는 분기가 없어야 한다.
        checkExistSameQuestionFlowLogic(survey.getQuestions());

        /**
         * 각 질문의 검증 로직을 진행해야함.
         * 1. 서술형은 없음.
         * 2. Range Bar는 답변을 설정한 Min, Max 밸류가 달라야함
         * 3. 객관식
         *    1. 객관식에서 답변할 수 있는 개수와 로직이 가지는 조건 답변 개수가 같아야함.
         *    2. 객관식에서 겹치는 분기문이 있으면 안됨.
         *    3. 객관식 답이 2개 이상인지
         *    4.:
         */

        //TODO 마지막으로 질문이 0개면 예외 던짐. 이거 기준이 뭐야

    }

    private void checkExistSameQuestionNumber(Survey survey) {
        Set<String> set = new HashSet<>();
        List<String> questionNumbers = extractQuestionNumbers(survey);
        for (String questionNumber : questionNumbers) {
            if (!set.add(questionNumber)) {
                throw new SameQuestionNumberException();
            }
        }
    }

    private List<String> extractQuestionNumbers(Survey survey) {
        return survey.getQuestions()
                .stream()
                .map(question -> question.getQuestionNumber())
                .collect(Collectors.toList());
    }

    private void checkExistSameQuestionFlowLogic(List<Question> questions) {
        for (Question question : questions) {
            if(question.getClass().)
        }
    }
}
