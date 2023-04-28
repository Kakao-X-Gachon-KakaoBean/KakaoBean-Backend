package com.kakaobean.core.unit.domain.survey;

import com.kakaobean.core.survey.domain.question.range.RangeQuestion;
import com.kakaobean.core.survey.exception.RangeQuestionBoundaryValueException;
import com.kakaobean.core.unit.UnitTest;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RangeQuestionTest extends UnitTest {

    @Test
    void failRegisterRangeBarQuestionCase(){
        //given
        RangeQuestion question = new RangeQuestion("title", "ex", "1", 1, 1, true);

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = Assertions.assertThatThrownBy(() -> {
            question.validate();
        });

        //then
        result.isInstanceOf(RangeQuestionBoundaryValueException.class);
        result.hasMessage("Range 질문의 최솟값과 최댓값이 동일합니다.");
    }

    @Test
    void successRegisterRangeBarQuestionCase(){
        //given
        RangeQuestion question = new RangeQuestion("title", "ex", "1", 1, 10, true);

        //when, then
        question.validate();;
    }
}
