package com.eyecon.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCoin is a Querydsl query type for Coin
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCoin extends EntityPathBase<Coin> {

    private static final long serialVersionUID = 742580357L;

    public static final QCoin coin = new QCoin("coin");

    public final NumberPath<Integer> coinCnt = createNumber("coinCnt", Integer.class);

    public final StringPath coinName = createString("coinName");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public QCoin(String variable) {
        super(Coin.class, forVariable(variable));
    }

    public QCoin(Path<? extends Coin> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCoin(PathMetadata metadata) {
        super(Coin.class, metadata);
    }

}

