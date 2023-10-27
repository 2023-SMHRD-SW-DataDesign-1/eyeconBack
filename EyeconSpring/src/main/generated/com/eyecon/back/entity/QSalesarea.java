package com.eyecon.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSalesarea is a Querydsl query type for Salesarea
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSalesarea extends EntityPathBase<Salesarea> {

    private static final long serialVersionUID = 2035128869L;

    public static final QSalesarea salesarea = new QSalesarea("salesarea");

    public final StringPath age = createString("age");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath place = createString("place");

    public final StringPath sex = createString("sex");

    public QSalesarea(String variable) {
        super(Salesarea.class, forVariable(variable));
    }

    public QSalesarea(Path<? extends Salesarea> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSalesarea(PathMetadata metadata) {
        super(Salesarea.class, metadata);
    }

}

