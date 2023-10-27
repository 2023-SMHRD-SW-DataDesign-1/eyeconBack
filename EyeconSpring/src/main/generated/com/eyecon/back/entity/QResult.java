package com.eyecon.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QResult is a Querydsl query type for Result
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResult extends EntityPathBase<Result> {

    private static final long serialVersionUID = 1075662097L;

    public static final QResult result = new QResult("result");

    public final StringPath beforeimg = createString("beforeimg");

    public final DatePath<java.util.Date> date = createDate("date", java.util.Date.class);

    public final StringPath email = createString("email");

    public final StringPath hitmap = createString("hitmap");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath placementimg = createString("placementimg");

    public final StringPath resultname = createString("resultname");

    public final StringPath route = createString("route");

    public final TimePath<java.sql.Time> time = createTime("time", java.sql.Time.class);

    public QResult(String variable) {
        super(Result.class, forVariable(variable));
    }

    public QResult(Path<? extends Result> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResult(PathMetadata metadata) {
        super(Result.class, metadata);
    }

}

