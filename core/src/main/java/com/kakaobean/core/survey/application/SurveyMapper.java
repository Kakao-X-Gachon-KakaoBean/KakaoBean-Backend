package com.kakaobean.core.survey.application;

import com.kakaobean.core.survey.application.dto.RegisterSurveyRequestDto;
import com.kakaobean.core.survey.application.dto.question.RegisterMultipleChoiceQuestionRequestDto;
import com.kakaobean.core.survey.application.dto.question.RegisterQuestionFlowLogicRequestDto;
import com.kakaobean.core.survey.application.dto.question.RegisterQuestionRequestDto;
import com.kakaobean.core.survey.domain.Survey;
import com.kakaobean.core.survey.domain.SurveyOwner;
import com.kakaobean.core.survey.domain.question.Question;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestion;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestionAnswer;
import com.kakaobean.core.survey.domain.question.multiplechoice.QuestionFlowLogic;
import com.kakaobean.core.survey.domain.question.multiplechoice.QuestionFlowLogicWithAnswerCondition;
import com.kakaobean.core.survey.exception.NoMatchingQuestionAnswerException;
import com.kakaobean.core.survey.exception.NoMatchingQuestionNumberException;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class SurveyMapper {

    public Survey mapFrom(RegisterSurveyRequestDto dto){
        Survey survey = new Survey(new SurveyOwner(dto.getMemberId()), createQuestion(dto));
        initNextQuestionForAllQuestions(survey.getQuestions(), dto.getDtoList());
        initChoiceQuestionLogic(survey.getQuestions(), dto.getDtoList());
        return survey;
    }

    private List<Question> createQuestion(RegisterSurveyRequestDto dto) {
        return dto.getDtoList()
                .stream()
                .map(questionDto -> questionDto.toEntity())
                .collect(Collectors.toList());
    }

    private void initNextQuestionForAllQuestions(List<Question> questions, List<RegisterQuestionRequestDto> dtoList) {
        for (int i = 0; i < questions.size(); i++) {
            RegisterQuestionRequestDto dto = dtoList.get(i);
            if(haveNotNextQuestion(dto)){
                continue;
            }
            Question initTarget = questions.get(i);
            Question nextQuestion = getNextQuestion(questions, dto);
            initTarget.addNextQuestion(nextQuestion);
        }
    }

    private boolean haveNotNextQuestion(RegisterQuestionRequestDto dto) {
        return dto.getNextQuestionNumber().equals("0");
    }

    private Question getNextQuestion(List<Question> questions, RegisterQuestionRequestDto dto) {
        return questions.stream()
                .filter(question -> question.getQuestionNumber().equals(dto.getNextQuestionNumber()))
                .findFirst()
                .orElseThrow(() -> new NoMatchingQuestionNumberException(dto.getNextQuestionNumber()));
    }

    /**
     * 분기점을 가지는 질문을 초기화함.
     */
    private void initChoiceQuestionLogic(
            List<Question> questions,
            List<RegisterQuestionRequestDto> dtoList
    ) {
        /**
         * 1. 먼저 순회하면서 분기점을 가지는 질문(현재는 Only 객관식) DTO을 찾는다.
         * 2. 찾는다면 Logic이 빈 값인지 찾는다.
         * 3. 빈 값이 아니라면 Logic을 생성한다.
         */
        for (int i = 0; i < dtoList.size(); i++) {
            RegisterQuestionRequestDto dto = dtoList.get(i);

            //답안에 따른 로직을 가진 질문(객관식)이 아니면 return; 현재 도메인 로직 상 객관식만 다음 로직을 가짐.
            if(!dto.hasQuestionFlowLogic()){
                continue;
            }

            //객관식 질문을 도출.
            RegisterMultipleChoiceQuestionRequestDto multipleChoiceDto = (RegisterMultipleChoiceQuestionRequestDto) dto;
            MultipleChoiceQuestion question = (MultipleChoiceQuestion )questions.get(i);

            initDetailQuestionFlowLogic(multipleChoiceDto, question, questions, question.getAnswers());
        }
    }


    /**
     * 분기점이 있는 질문에서만 실행된다.
     */
    private void initDetailQuestionFlowLogic(
            RegisterMultipleChoiceQuestionRequestDto multipleChoiceDto,
            MultipleChoiceQuestion ownerQuestion,
            List<Question> questions,
            List<MultipleChoiceQuestionAnswer> answers
    ) {
        if(multipleChoiceDto.getConditions().isEmpty()){
            return;
        }
        ownerQuestion.addLogics(
                multipleChoiceDto
                        .getConditions()
                        .stream()
                        .map(condition -> initFlowLogic(ownerQuestion, questions, answers, condition))
                        .collect(toList())
        );
    }


    private QuestionFlowLogic initFlowLogic(
            MultipleChoiceQuestion ownerQuestion,
            List<Question> questions,
            List<MultipleChoiceQuestionAnswer> answers,
            RegisterQuestionFlowLogicRequestDto condition
    ) {
        //로직을 생성함.
        QuestionFlowLogic flowLogic = createCreateFlowLogic(ownerQuestion, questions, condition);

        //로직에 조건들을 초기화함.
        flowLogic.addConditions(registerFlowCondition(answers, flowLogic, condition));
        return flowLogic;
    }


    /**
     * 단순히 분기점을 생성함.
     */
    private QuestionFlowLogic createCreateFlowLogic(
            MultipleChoiceQuestion ownerQuestion,
            List<Question> questions,
            RegisterQuestionFlowLogicRequestDto condition
    ) {
        return new QuestionFlowLogic(
                ownerQuestion,
                registerLogicNextQuestion(
                        questions,
                        condition.getNextQuestionNumber()
                )
        );
    }

    /**
     * 질문 번호를 통해 이동할 질문을 뽑아냄.
     */
    private Question registerLogicNextQuestion(List<Question> questions, String nextQuestionNumber) {
        return questions.stream()
                .filter(question -> question.getQuestionNumber().equals(nextQuestionNumber))
                .findFirst()
                .orElseThrow(() -> new NoMatchingQuestionNumberException(nextQuestionNumber));
    }

    /**
     * 분기점은 어떤 답을 고르느냐에 따라 달라짐.
     * 그것을 초기화함.
     */
    private List<QuestionFlowLogicWithAnswerCondition> registerFlowCondition(
            List<MultipleChoiceQuestionAnswer> answers,
            QuestionFlowLogic flowLogic,
            RegisterQuestionFlowLogicRequestDto condition
    ) {
        return condition.getConditionOfQuestionAnswers()
                .stream()
                .map(answerString -> new QuestionFlowLogicWithAnswerCondition(
                        flowLogic,
                        answers.stream()
                                .filter(answer -> answer.getContent().equals(answerString))
                                .findFirst()
                                .orElseThrow(() -> new NoMatchingQuestionAnswerException(answerString))

                ))
                .collect(Collectors.toList());
    }
}
