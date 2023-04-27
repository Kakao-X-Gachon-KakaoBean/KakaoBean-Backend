package com.kakaobean.core.unit.survey.domain;

import com.kakaobean.core.survey.domain.question.essay.EssayQuestion;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestion;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestionAnswer;
import com.kakaobean.core.survey.domain.question.multiplechoice.QuestionFlowLogic;
import com.kakaobean.core.survey.domain.question.multiplechoice.QuestionFlowLogicWithAnswerCondition;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.List;

public class MultipleQuestionTest {

    @Test
    void test(){

        MultipleChoiceQuestionAnswer firstAnswer = new MultipleChoiceQuestionAnswer("1번 문항");
        MultipleChoiceQuestionAnswer secondAnswer = new MultipleChoiceQuestionAnswer("2번 문항");
        MultipleChoiceQuestionAnswer thirdAnswer = new MultipleChoiceQuestionAnswer("3번 문항");

        MultipleChoiceQuestion question = new MultipleChoiceQuestion("title", "ex", "1", List.of(firstAnswer, secondAnswer, thirdAnswer), 2, false);

        EssayQuestion nextQuestion1 = new EssayQuestion("title", "ex", "2",  true);
        EssayQuestion nextQuestion2 = new EssayQuestion("title", "ex", "3",  true);

        QuestionFlowLogic questionFlowLogic1 = new QuestionFlowLogic(question, nextQuestion1);
        QuestionFlowLogicWithAnswerCondition condition = new QuestionFlowLogicWithAnswerCondition(questionFlowLogic1, firstAnswer);
        QuestionFlowLogicWithAnswerCondition condition2 = new QuestionFlowLogicWithAnswerCondition(questionFlowLogic1, secondAnswer);
        questionFlowLogic1.addConditions(List.of(condition, condition2));

        QuestionFlowLogic questionFlowLogic2 = new QuestionFlowLogic(question, nextQuestion2);
        QuestionFlowLogicWithAnswerCondition condition3 = new QuestionFlowLogicWithAnswerCondition(questionFlowLogic2, firstAnswer);
        QuestionFlowLogicWithAnswerCondition condition4 = new QuestionFlowLogicWithAnswerCondition(questionFlowLogic2, secondAnswer);
        questionFlowLogic2.addConditions(List.of(condition3, condition4));

        question.addLogics(List.of(questionFlowLogic1, questionFlowLogic2));

        Assertions.assertThatThrownBy(() -> {
            question.validate();
        }).isInstanceOf(RuntimeException.class);
    }

}
