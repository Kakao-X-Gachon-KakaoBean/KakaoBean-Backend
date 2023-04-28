package com.kakaobean.core.response.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRespondent is a Querydsl query type for Respondent
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QRespondent extends BeanPath<Respondent> {

    private static final long serialVersionUID = -1804098598L;

    public static final QRespondent respondent = new QRespondent("respondent");

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public QRespondent(String variable) {
        super(Respondent.class, forVariable(variable));
    }

    public QRespondent(Path<? extends Respondent> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRespondent(PathMetadata metadata) {
        super(Respondent.class, metadata);
    }

}

