package com.kakaobean.core.unit.domain.survey;

import com.kakaobean.core.survey.domain.question.essay.EssayQuestion;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestion;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestionAnswer;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestionFlowLogic;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestionFlowLogicCondition;
import com.kakaobean.core.survey.exception.questionflowlogic.ExistSameConditionException;
import com.kakaobean.core.survey.exception.questionflowlogic.ExistSameLogicException;
import com.kakaobean.core.unit.UnitTest;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

public class MultipleQuestionTest extends UnitTest {

    MultipleChoiceQuestionAnswer firstAnswer;
    MultipleChoiceQuestionAnswer secondAnswer;
    MultipleChoiceQuestionAnswer thirdAnswer;

    EssayQuestion nextQuestion1;
    EssayQuestion nextQuestion2;

    @BeforeEach
    void beforeEach(){
        firstAnswer = new MultipleChoiceQuestionAnswer("1번 문항");
        secondAnswer = new MultipleChoiceQuestionAnswer("2번 문항");
        thirdAnswer = new MultipleChoiceQuestionAnswer("3번 문항");

        nextQuestion1 = new EssayQuestion("title", "ex", "2",  true);
        nextQuestion2 = new EssayQuestion("title", "ex", "3",  true);
    }

    @Test
    @DisplayName("중복되는 조건이 존재, 그러나 다음 질문이 다르면 객관식 validate 메서드가 실패한다.")
    void failMultipleQuestionValidationCase1(){

        //given
        MultipleChoiceQuestion question = new MultipleChoiceQuestion("title", "ex", "1", List.of(firstAnswer, secondAnswer, thirdAnswer), 2, false);

        MultipleChoiceQuestionFlowLogic questionFlowLogic1 = new MultipleChoiceQuestionFlowLogic(question, nextQuestion1);
        MultipleChoiceQuestionFlowLogicCondition condition = new MultipleChoiceQuestionFlowLogicCondition(questionFlowLogic1, firstAnswer);
        MultipleChoiceQuestionFlowLogicCondition condition2 = new MultipleChoiceQuestionFlowLogicCondition(questionFlowLogic1, secondAnswer);
        questionFlowLogic1.addConditions(List.of(condition, condition2));

        MultipleChoiceQuestionFlowLogic questionFlowLogic2 = new MultipleChoiceQuestionFlowLogic(question, nextQuestion2);
        MultipleChoiceQuestionFlowLogicCondition condition3 = new MultipleChoiceQuestionFlowLogicCondition(questionFlowLogic2, firstAnswer);
        MultipleChoiceQuestionFlowLogicCondition condition4 = new MultipleChoiceQuestionFlowLogicCondition(questionFlowLogic2, secondAnswer);
        questionFlowLogic2.addConditions(List.of(condition3, condition4));

        question.addLogics(List.of(questionFlowLogic1, questionFlowLogic2));

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = Assertions.assertThatThrownBy(() -> {
            question.validate();
        });

        //then
        result.isInstanceOf(ExistSameConditionException.class);
        result.hasMessage("동일한 조건을 가진 로직이 2개 이상 존재합니다.");
    }


    @Test
    @DisplayName("동일한 로직이 존재하면, 객관식 validate 메서드가 실패한다.")
    void failMultipleQuestionValidationCase2(){

        //given
        MultipleChoiceQuestion question = new MultipleChoiceQuestion("title", "ex", "1", List.of(firstAnswer, secondAnswer, thirdAnswer), 2, false);

        MultipleChoiceQuestionFlowLogic questionFlowLogic1 = new MultipleChoiceQuestionFlowLogic(question, nextQuestion1);
        MultipleChoiceQuestionFlowLogicCondition condition = new MultipleChoiceQuestionFlowLogicCondition(questionFlowLogic1, firstAnswer);
        MultipleChoiceQuestionFlowLogicCondition condition2 = new MultipleChoiceQuestionFlowLogicCondition(questionFlowLogic1, secondAnswer);
        questionFlowLogic1.addConditions(List.of(condition, condition2));

        MultipleChoiceQuestionFlowLogic questionFlowLogic2 = new MultipleChoiceQuestionFlowLogic(question, nextQuestion1);
        MultipleChoiceQuestionFlowLogicCondition condition3 = new MultipleChoiceQuestionFlowLogicCondition(questionFlowLogic2, firstAnswer);
        MultipleChoiceQuestionFlowLogicCondition condition4 = new MultipleChoiceQuestionFlowLogicCondition(questionFlowLogic2, secondAnswer);
        questionFlowLogic2.addConditions(List.of(condition3, condition4));

        question.addLogics(List.of(questionFlowLogic1, questionFlowLogic2));

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = Assertions.assertThatThrownBy(() -> {
            question.validate();
        });

        //then
        result.isInstanceOf(ExistSameLogicException.class);
        result.hasMessage("동일한 로직이 존재합니다.");
    }

    @Test
    @DisplayName("로직의 조건인 답변의 종류만 다르면 같은 다음 질문을 가질 수 있다.")
    void successMultipleQuestionValidation(){

        //given
        MultipleChoiceQuestion question = new MultipleChoiceQuestion("title", "ex", "1", List.of(firstAnswer, secondAnswer, thirdAnswer), 2, false);

        MultipleChoiceQuestionFlowLogic questionFlowLogic1 = new MultipleChoiceQuestionFlowLogic(question, nextQuestion1);
        MultipleChoiceQuestionFlowLogicCondition condition = new MultipleChoiceQuestionFlowLogicCondition(questionFlowLogic1, firstAnswer);
        MultipleChoiceQuestionFlowLogicCondition condition2 = new MultipleChoiceQuestionFlowLogicCondition(questionFlowLogic1, secondAnswer);
        questionFlowLogic1.addConditions(List.of(condition, condition2));

        MultipleChoiceQuestionFlowLogic questionFlowLogic2 = new MultipleChoiceQuestionFlowLogic(question, nextQuestion1);
        MultipleChoiceQuestionFlowLogicCondition condition3 = new MultipleChoiceQuestionFlowLogicCondition(questionFlowLogic2, firstAnswer);
        questionFlowLogic2.addConditions(List.of(condition3));

        question.addLogics(List.of(questionFlowLogic1, questionFlowLogic2));

        //when, then
        question.validate();
    }

}
