package com.kakaobean.core.survey.domain.question.multiplechoice;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestionFlowLogic is a Querydsl query type for QuestionFlowLogic
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestionFlowLogic extends EntityPathBase<QuestionFlowLogic> {

    private static final long serialVersionUID = 867001784L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestionFlowLogic questionFlowLogic = new QQuestionFlowLogic("questionFlowLogic");

    public final com.kakaobean.core.common.domain.QBaseEntity _super = new com.kakaobean.core.common.domain.QBaseEntity(this);

    public final ListPath<QuestionFlowLogicWithAnswerCondition, QQuestionFlowLogicWithAnswerCondition> conditions = this.<QuestionFlowLogicWithAnswerCondition, QQuestionFlowLogicWithAnswerCondition>createList("conditions", QuestionFlowLogicWithAnswerCondition.class, QQuestionFlowLogicWithAnswerCondition.class, PathInits.DIRECT2);

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.kakaobean.core.survey.domain.question.QQuestion nextQuestion;

    public final QMultipleChoiceQuestion question;

    //inherited
    public final EnumPath<com.kakaobean.core.common.domain.BaseStatus> status = _super.status;

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QQuestionFlowLogic(String variable) {
        this(QuestionFlowLogic.class, forVariable(variable), INITS);
    }

    public QQuestionFlowLogic(Path<? extends QuestionFlowLogic> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestionFlowLogic(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestionFlowLogic(PathMetadata metadata, PathInits inits) {
        this(QuestionFlowLogic.class, metadata, inits);
    }

    public QQuestionFlowLogic(Class<? extends QuestionFlowLogic> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.nextQuestion = inits.isInitialized("nextQuestion") ? new com.kakaobean.core.survey.domain.question.QQuestion(forProperty("nextQuestion"), inits.get("nextQuestion")) : null;
        this.question = inits.isInitialized("question") ? new QMultipleChoiceQuestion(forProperty("question"), inits.get("question")) : null;
    }

}

