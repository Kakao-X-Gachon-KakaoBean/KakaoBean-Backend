package com.kakaobean.core.survey.question.multiplechoice;

import com.kakaobean.core.survey.Survey;
import com.kakaobean.core.survey.question.Question;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("multiple_choice_question")
public class MultipleChoiceQuestion extends Question {

    @OneToMany(mappedBy = "question")
    private List<MultipleChoiceQuestionAnswer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "question")
    private List<QuestionFlowLogic> logics = new ArrayList<>();

    public MultipleChoiceQuestion(Survey survey, String title, String explanation) {
        super(survey, title, explanation);
    }
}
