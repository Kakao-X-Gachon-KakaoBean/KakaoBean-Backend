package com.kakaobean.core.unit.domain.survey;

import com.kakaobean.core.survey.domain.question.essay.EssayQuestion;
import org.junit.jupiter.api.Test;

public class EssayQuestionTest {


    @Test
    void registerSuccessTest(){
        //given
        EssayQuestion question = new EssayQuestion("title", "ex", "1", false);

        //when, then
        question.validate();
    }
}
