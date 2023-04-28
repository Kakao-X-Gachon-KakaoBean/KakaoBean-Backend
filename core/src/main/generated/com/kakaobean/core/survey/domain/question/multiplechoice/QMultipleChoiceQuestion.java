package com.kakaobean.core.survey.domain.question.multiplechoice;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMultipleChoiceQuestion is a Querydsl query type for MultipleChoiceQuestion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMultipleChoiceQuestion extends EntityPathBase<MultipleChoiceQuestion> {

    private static final long serialVersionUID = -586884247L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMultipleChoiceQuestion multipleChoiceQuestion = new QMultipleChoiceQuestion("multipleChoiceQuestion");

    public final com.kakaobean.core.survey.domain.question.QQuestion _super;

    public final ListPath<MultipleChoiceQuestionAnswer, QMultipleChoiceQuestionAnswer> answers = this.<MultipleChoiceQuestionAnswer, QMultipleChoiceQuestionAnswer>createList("answers", MultipleChoiceQuestionAnswer.class, QMultipleChoiceQuestionAnswer.class, PathInits.DIRECT2);

    //inherited
    public final StringPath createdAt;

    //inherited
    public final StringPath explanation;

    //inherited
    public final BooleanPath finalQuestion;

    //inherited
    public final NumberPath<Long> id;

    public final ListPath<QuestionFlowLogic, QQuestionFlowLogic> logics = this.<QuestionFlowLogic, QQuestionFlowLogic>createList("logics", QuestionFlowLogic.class, QQuestionFlowLogic.class, PathInits.DIRECT2);

    // inherited
    public final com.kakaobean.core.survey.domain.question.QQuestion nextQuestion;

    public final NumberPath<Integer> numberOfAnswerChoices = createNumber("numberOfAnswerChoices", Integer.class);

    //inherited
    public final StringPath questionNumber;

    //inherited
    public final EnumPath<com.kakaobean.core.common.domain.BaseStatus> status;

    // inherited
    public final com.kakaobean.core.survey.domain.QSurvey survey;

    //inherited
    public final StringPath title;

    //inherited
    public final StringPath updatedAt;

    public QMultipleChoiceQuestion(String variable) {
        this(MultipleChoiceQuestion.class, forVariable(variable), INITS);
    }

    public QMultipleChoiceQuestion(Path<? extends MultipleChoiceQuestion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMultipleChoiceQuestion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMultipleChoiceQuestion(PathMetadata metadata, PathInits inits) {
        this(MultipleChoiceQuestion.class, metadata, inits);
    }

    public QMultipleChoiceQuestion(Class<? extends MultipleChoiceQuestion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new com.kakaobean.core.survey.domain.question.QQuestion(type, metadata, inits);
        this.createdAt = _super.createdAt;
        this.explanation = _super.explanation;
        this.finalQuestion = _super.finalQuestion;
        this.id = _super.id;
        this.nextQuestion = _super.nextQuestion;
        this.questionNumber = _super.questionNumber;
        this.status = _super.status;
        this.survey = _super.survey;
        this.title = _super.title;
        this.updatedAt = _super.updatedAt;
    }

}

