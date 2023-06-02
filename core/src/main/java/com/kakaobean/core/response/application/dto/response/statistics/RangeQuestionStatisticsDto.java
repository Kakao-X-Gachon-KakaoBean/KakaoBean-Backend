package com.kakaobean.core.response.application.dto.response.statistics;

import com.kakaobean.core.response.application.dto.response.SurveyResponseDto;
import com.kakaobean.core.response.application.dto.response.question.QuestionResponseDto;
import com.kakaobean.core.response.application.dto.response.question.RangeQuestionResponseDto;
import com.kakaobean.core.survey.application.dto.QuestionDTOType;
import com.kakaobean.core.survey.domain.question.range.RangeQuestion;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
@Getter
public class RangeQuestionStatisticsDto extends QuestionStatisticsDto {

    private Integer min;

    private Integer max;

    private List<AnswerStatisticsDto> answers = new ArrayList<>();

    @Builder
    public RangeQuestionStatisticsDto(QuestionDTOType type,
                                      String title,
                                      String explanation,
                                      Integer min,
                                      Integer max,
                                      RangeQuestion rangeQuestion,
                                      List<SurveyResponseDto> allResponses) {
        super(type, title, explanation);
        this.min = min;
        this.max = max;
        addAnswer(rangeQuestion, allResponses);
    }

    public void addAnswer(RangeQuestion rangeQuestion, List<SurveyResponseDto> allResponses){

        List<Integer> answerList = new ArrayList<>();

        for(SurveyResponseDto surveyResponse : allResponses) {
            List<QuestionResponseDto> questionResponses = surveyResponse.getQuestionResponses();

            for (QuestionResponseDto questionResponse : questionResponses) {
                if (rangeQuestion.getId() == questionResponse.getQuestionId()){
                    RangeQuestionResponseDto rangeQuestionResponse = (RangeQuestionResponseDto)questionResponse;
                    answerList.add(rangeQuestionResponse.getAnswer());

                }
            }
        }

        calculatePercentage(answerList);
    }

    public void calculatePercentage(List<Integer> answerList){
        // range 질문에 대한 전체 답변 수
        int totalAnswerSize = answerList.size();

        // List<Integer>의 요소를 카운트하여 각 숫자가 몇 번 나타나는지 기록하는 Map<Integer, Integer>을 생성한다.
        // 이때, 키(key)는 숫자가 되고 값(value)은 해당 숫자의 등장 횟수가 됨.
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int number : answerList) {
            countMap.put(number, countMap.getOrDefault(number, 0) + 1);
        }

        // Map<Integer, Integer>에서 등장 횟수(value)를 기준으로 내림차순 정렬된 List<Map.Entry<Integer, Integer>>를 생성한다.
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(countMap.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // 퍼센트를 구하고 answer 변수에 add
        for (Map.Entry<Integer, Integer> Entry : entryList) {
            String key = Entry.getKey().toString();
            Integer value = Entry.getValue();
            Integer percentage = Math.round((float) value / totalAnswerSize * 100);

            this.answers.add(new AnswerStatisticsDto(key, percentage));
        }
    }
}
