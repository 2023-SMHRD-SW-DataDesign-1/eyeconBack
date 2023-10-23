package com.eyecon.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTokens is a Querydsl query type for Tokens
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTokens extends EntityPathBase<Token> {

    private static final long serialVersionUID = 1141901966L;

    public static final QTokens tokens = new QTokens("tokens");

    public final BooleanPath expired = createBoolean("expired");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath revoked = createBoolean("revoked");

    public final StringPath token = createString("token");

    public final EnumPath<TokenType> tokenType = createEnum("tokenType", TokenType.class);

    public final StringPath userName = createString("userName");

    public QTokens(String variable) {
        super(Token.class, forVariable(variable));
    }

    public QTokens(Path<? extends Token> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTokens(PathMetadata metadata) {
        super(Token.class, metadata);
    }

}

