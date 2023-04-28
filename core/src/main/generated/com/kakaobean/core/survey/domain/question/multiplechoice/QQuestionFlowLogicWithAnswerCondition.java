package com.kakaobean.core.survey.domain.question.multiplechoice;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestionFlowLogicWithAnswerCondition is a Querydsl query type for QuestionFlowLogicWithAnswerCondition
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestionFlowLogicWithAnswerCondition extends EntityPathBase<QuestionFlowLogicWithAnswerCondition> {

    private static final long serialVersionUID = 931994751L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestionFlowLogicWithAnswerCondition questionFlowLogicWithAnswerCondition = new QQuestionFlowLogicWithAnswerCondition("questionFlowLogicWithAnswerCondition");

    public final com.kakaobean.core.common.domain.QBaseEntity _super = new com.kakaobean.core.common.domain.QBaseEntity(this);

    public final QMultipleChoiceQuestionAnswer answer;

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QQuestionFlowLogic logic;

    //inherited
    public final EnumPath<com.kakaobean.core.common.domain.BaseStatus> status = _super.status;

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QQuestionFlowLogicWithAnswerCondition(String variable) {
        this(QuestionFlowLogicWithAnswerCondition.class, forVariable(variable), INITS);
    }

    public QQuestionFlowLogicWithAnswerCondition(Path<? extends QuestionFlowLogicWithAnswerCondition> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestionFlowLogicWithAnswerCondition(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestionFlowLogicWithAnswerCondition(PathMetadata metadata, PathInits inits) {
        this(QuestionFlowLogicWithAnswerCondition.class, metadata, inits);
    }

    public QQuestionFlowLogicWithAnswerCondition(Class<? extends QuestionFlowLogicWithAnswerCondition> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.answer = inits.isInitialized("answer") ? new QMultipleChoiceQuestionAnswer(forProperty("answer"), inits.get("answer")) : null;
        this.logic = inits.isInitialized("logic") ? new QQuestionFlowLogic(forProperty("logic"), inits.get("logic")) : null;
    }

}

