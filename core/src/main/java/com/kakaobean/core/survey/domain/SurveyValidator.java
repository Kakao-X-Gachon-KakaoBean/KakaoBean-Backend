package com.kakaobean.core.survey.domain;

import com.kakaobean.core.survey.domain.question.Question;
import com.kakaobean.core.survey.exception.SameQuestionNumberException;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SurveyValidator {

    /**
     * 질문 도메인 검증
     */
    public void validate(Survey survey){
        checkExistSameQuestionNumber(survey);
        validateByQuestionType(survey);
    }

    /**
     * 질문 번호가 겹치는게 없어야한다.
     */
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
                .map(Question::getQuestionNumber)
                .collect(Collectors.toList());
    }

    /**
     * 원래 검증은 한 곳에 모아놓고 하는게 명시적이지만 추상화를 도입했으므로 코드가 너무 길어질 거 같아 오버라이딩을 사용.
     *
     * 각 질문의 검증 로직을 진행해야함.
     * 1. 서술형은 없음.
     * 2. Range Bar는 답변을 설정한 Min, Max 밸류가 달라야함
     * 3. 객관식
     *    1. 객관식에서 답변할 수 있는 개수와 로직이 가지는 조건 답변 개수가 같아야함.
     *    2. 객관식에서 겹치는 분기문이 있으면 안됨.
     *
     */
    private void validateByQuestionType(Survey survey) {
        survey.getQuestions().forEach(Question::validate);
    }
}
