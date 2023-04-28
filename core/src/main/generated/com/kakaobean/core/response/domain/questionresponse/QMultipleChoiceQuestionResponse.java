package com.kakaobean.core.response.domain.questionresponse;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMultipleChoiceQuestionResponse is a Querydsl query type for MultipleChoiceQuestionResponse
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMultipleChoiceQuestionResponse extends EntityPathBase<MultipleChoiceQuestionResponse> {

    private static final long serialVersionUID = -478336005L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMultipleChoiceQuestionResponse multipleChoiceQuestionResponse = new QMultipleChoiceQuestionResponse("multipleChoiceQuestionResponse");

    public final QQuestionResponse _super;

    public final StringPath answer = createString("answer");

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final NumberPath<Long> questionId;

    // inherited
    public final com.kakaobean.core.response.domain.QSurveyResponse surveyResponse;

    public QMultipleChoiceQuestionResponse(String variable) {
        this(MultipleChoiceQuestionResponse.class, forVariable(variable), INITS);
    }

    public QMultipleChoiceQuestionResponse(Path<? extends MultipleChoiceQuestionResponse> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMultipleChoiceQuestionResponse(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMultipleChoiceQuestionResponse(PathMetadata metadata, PathInits inits) {
        this(MultipleChoiceQuestionResponse.class, metadata, inits);
    }

    public QMultipleChoiceQuestionResponse(Class<? extends MultipleChoiceQuestionResponse> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QQuestionResponse(type, metadata, inits);
        this.id = _super.id;
        this.questionId = _super.questionId;
        this.surveyResponse = _super.surveyResponse;
    }

}

