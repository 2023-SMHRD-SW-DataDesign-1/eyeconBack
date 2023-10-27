package com.eyecon.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPayment is a Querydsl query type for Payment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPayment extends EntityPathBase<Payment> {

    private static final long serialVersionUID = 1396525970L;

    public static final QPayment payment = new QPayment("payment");

    public final StringPath category = createString("category");

    public final NumberPath<Integer> coin = createNumber("coin", Integer.class);

    public final NumberPath<Integer> coinId = createNumber("coinId", Integer.class);

    public final DatePath<java.sql.Date> date = createDate("date", java.sql.Date.class);

    public final StringPath email = createString("email");

    public final StringPath finance = createString("finance");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath number = createString("number");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final TimePath<java.sql.Time> time = createTime("time", java.sql.Time.class);

    public QPayment(String variable) {
        super(Payment.class, forVariable(variable));
    }

    public QPayment(Path<? extends Payment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPayment(PathMetadata metadata) {
        super(Payment.class, metadata);
    }

}

