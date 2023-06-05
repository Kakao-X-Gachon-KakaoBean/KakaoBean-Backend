package com.kakaobean.core.response.application.dto.response.statistics;

import com.kakaobean.core.response.application.dto.response.SurveyResponseDto;
import com.kakaobean.core.response.application.dto.response.question.MultipleChoiceQuestionResponseDto;
import com.kakaobean.core.response.application.dto.response.question.QuestionResponseDto;
import com.kakaobean.core.survey.application.dto.QuestionDTOType;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestion;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
@Getter
public class MultipleQuestionStatisticsDto extends QuestionStatisticsDto {

    private List<AnswerStatisticsDto> answers = new ArrayList<>();

    // 테스트용
    public MultipleQuestionStatisticsDto(QuestionDTOType type,
                                         String title,
                                         String explanation,
                                         List<AnswerStatisticsDto> answers) {
        super(type, title, explanation);
        this.answers = answers;
    }

    public MultipleQuestionStatisticsDto(QuestionDTOType type,
                                         String title,
                                         String explanation,
                                         MultipleChoiceQuestion multipleChoiceQuestion,
                                         List<SurveyResponseDto> allResponses
                                         ) {
        super(type, title, explanation);
        addAnswer(multipleChoiceQuestion, allResponses);
    }

    public void addAnswer(MultipleChoiceQuestion multipleChoiceQuestion, List<SurveyResponseDto> allResponses){

        List<String> answerList = new ArrayList<>();

        for(SurveyResponseDto surveyResponse : allResponses) {
            List<QuestionResponseDto> questionResponses = surveyResponse.getQuestionResponses();

            for (QuestionResponseDto questionResponse : questionResponses) {
                if (multipleChoiceQuestion.getId() == questionResponse.getQuestionId()){
                    MultipleChoiceQuestionResponseDto multipleChoiceQuestionResponse = (MultipleChoiceQuestionResponseDto) questionResponse;

                    // 객관식은 복수 선택이 있을 수 있다는 것을 고려함
                    multipleChoiceQuestionResponse.getAnswers()
                            .forEach(multipleChoiceQuestionAnswerResponse -> answerList.add(multipleChoiceQuestionAnswerResponse));
                }
            }
        }

        calculatePercentage(answerList);
    }

    public void calculatePercentage(List<String> answerList){
        // range 질문에 대한 전체 답변 수
        int totalAnswerSize = answerList.size();

        // List의 요소를 카운트하여 각 요소 몇 번 나타나는지 기록하는 Map을 생성한다.
        // 이때, 키(key)는 객관식 질문의 문항이 되고 값(value)은 해당 문항의 등장 횟수가 됨.
        Map<String, Integer> countMap = new HashMap<>();
        for (String answer : answerList) {
            countMap.put(answer, countMap.getOrDefault(answer, 0) + 1);
        }

        // Map<Integer, Integer>에서 등장 횟수(value)를 기준으로 내림차순 정렬된 List<Map.Entry<Integer, Integer>>를 생성한다.
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(countMap.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // 퍼센트를 구하고 answer 변수에 add
        for (Map.Entry<String, Integer> Entry : entryList) {
            String key = Entry.getKey();
            Integer value = Entry.getValue();
            Integer percentage = Math.round((float) value / totalAnswerSize * 100);

            this.answers.add(new AnswerStatisticsDto(key, percentage));
        }
    }

}
