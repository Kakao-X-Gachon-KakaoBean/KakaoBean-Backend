package com.kakaobean.core.survey.application.dto.question;


import com.kakaobean.core.survey.domain.question.Question;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestion;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestionAnswer;
import com.kakaobean.core.survey.domain.question.multiplechoice.QuestionFlowLogic;
import com.kakaobean.core.survey.domain.question.multiplechoice.QuestionFlowLogicWithAnswerCondition;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Getter
@NoArgsConstructor
public class RegisterMultipleChoiceQuestionRequestDto extends RegisterQuestionRequestDto{

    private Integer numberOfAnswerChoices;
    private List<String> answers;
    private List<RegisterQuestionFlowLogicRequestDto> conditions;

    public RegisterMultipleChoiceQuestionRequestDto(
            String title,
            String explanation,
            String questionNumber,
            Integer numberOfAnswerChoices,
            List<String> answers,
            List<RegisterQuestionFlowLogicRequestDto> conditions
    ) {
        super(title, explanation, questionNumber);
        this.numberOfAnswerChoices = numberOfAnswerChoices;
        this.answers = answers;
        this.conditions = conditions;
    }

    @Override
    protected Question createdDetailQuestionEntity() {
        MultipleChoiceQuestion question = new MultipleChoiceQuestion(
                title,
                explanation,
                questionNumber,
                createMultipleChoiceAnswers(),
                numberOfAnswerChoices
        );

        return question;
    }

    private List<MultipleChoiceQuestionAnswer> createMultipleChoiceAnswers() {
        return answers.stream()
                .map(MultipleChoiceQuestionAnswer::new)
                .collect(toList());
    }

    @Override
    public boolean hasQuestionFlowLogic() {
        return true;
    }

    public void initDetailQuestionFlowLogic(
            MultipleChoiceQuestion ownerQuestion,
            List<Question> questions,
            List<MultipleChoiceQuestionAnswer> answers
    ) {
        if(conditions.isEmpty()){
            return;
        }
        ownerQuestion.addLogics(
                conditions.
                        stream()
                        .map(condition -> {
                            QuestionFlowLogic flowLogic = createCreateFlowLogic(ownerQuestion, questions, condition);
                            flowLogic.addConditions(registerFlowCondition(answers, flowLogic, condition));
                            return flowLogic;
                        })
                        .collect(toList())
        );
    }

    private QuestionFlowLogic createCreateFlowLogic(
            MultipleChoiceQuestion ownerQuestion,
            List<Question> questions,
            RegisterQuestionFlowLogicRequestDto condition
    ) {
        return new QuestionFlowLogic(
                ownerQuestion,
                registerNextQuestion(questions, condition.getNextQuestionNumber())
        );
    }

    private Question registerNextQuestion(List<Question> questions, String nextQuestionNumber) {
        return questions.stream()
                .filter(question -> question.getQuestionNumber().equals(nextQuestionNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("해당하는 질문의 번호가 없습니다"));
    }

    private List<QuestionFlowLogicWithAnswerCondition> registerFlowCondition(
            List<MultipleChoiceQuestionAnswer> answers,
            QuestionFlowLogic flowLogic,
            RegisterQuestionFlowLogicRequestDto condition
    ) {
//        List<String> answerContents = answers
//                .stream()
//                .map(answer -> answer.getContent())
//                .collect(toList());
//
//        answerContents.stream()
//                        .map(answerContent -> new QuestionFlowLogicWithAnswerCondition(
//                                flowLogic,
//                                condition.getConditionOfQuestionAnswers()
//                                        .stream()
//                                        .filter(answerDto -> answerDto.equals(answerContent))
//                                        .findFirst()
//                                        .orElseThrow(() -> new RuntimeException("오류"))
//                        ))
//                                .collect(Collectors.toList());



        return condition.getConditionOfQuestionAnswers()
                .stream()
                .map(answerString -> new QuestionFlowLogicWithAnswerCondition(
                        flowLogic,
                        answers.stream()
                                .filter(answer -> answer.getContent().equals(answerString))
                                .findFirst()
                                .get()

                ))
                .collect(Collectors.toList());
    }
}
