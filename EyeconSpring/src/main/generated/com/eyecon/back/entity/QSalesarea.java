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


    public final StringPath category = createString("category");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath income = createString("income");

    public final StringPath maxday = createString("maxday");

    public final StringPath population = createString("population");

    public final StringPath sex = createString("sex");

    public final StringPath storecnt = createString("storecnt");


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

