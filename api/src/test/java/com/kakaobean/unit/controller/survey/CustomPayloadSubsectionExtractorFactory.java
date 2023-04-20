package com.kakaobean.unit.controller.survey;

public class CustomPayloadSubsectionExtractorFactory{

    public static MultipleChoiceQuestionPayloadSubsectionExtractor getMultipleChoiceQuestionExtractor(){
        return new MultipleChoiceQuestionPayloadSubsectionExtractor();
    }

    public static RangeQuestionPayloadSubsectionExtractor getRangeQuestionExtractor(){
        return new RangeQuestionPayloadSubsectionExtractor();
    }

    public static EssayQuestionPayloadSubsectionExtractor getEssayQuestionExtractor(){
        return new EssayQuestionPayloadSubsectionExtractor();
    }
}