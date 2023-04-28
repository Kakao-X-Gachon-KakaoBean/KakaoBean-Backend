package com.kakaobean.core.response.domain.questionresponse;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRangeQuestionResponse is a Querydsl query type for RangeQuestionResponse
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRangeQuestionResponse extends EntityPathBase<RangeQuestionResponse> {

    private static final long serialVersionUID = 126575233L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRangeQuestionResponse rangeQuestionResponse = new QRangeQuestionResponse("rangeQuestionResponse");

    public final QQuestionResponse _super;

    public final NumberPath<Integer> answerValue = createNumber("answerValue", Integer.class);

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final NumberPath<Long> questionId;

    // inherited
    public final com.kakaobean.core.response.domain.QSurveyResponse surveyResponse;

    public QRangeQuestionResponse(String variable) {
        this(RangeQuestionResponse.class, forVariable(variable), INITS);
    }

    public QRangeQuestionResponse(Path<? extends RangeQuestionResponse> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRangeQuestionResponse(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRangeQuestionResponse(PathMetadata metadata, PathInits inits) {
        this(RangeQuestionResponse.class, metadata, inits);
    }

    public QRangeQuestionResponse(Class<? extends RangeQuestionResponse> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QQuestionResponse(type, metadata, inits);
        this.id = _super.id;
        this.questionId = _super.questionId;
        this.surveyResponse = _super.surveyResponse;
    }

}

