package com.eyecon.back.repository;

import java.util.List;

import com.eyecon.back.entity.QToken;
import com.eyecon.back.entity.Token;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.NonNull;


public class TokenRepositoryCustomImpl implements TokenRepositoryCustom {
		
	private final JPAQueryFactory queryFactory;

	public TokenRepositoryCustomImpl(JPAQueryFactory queryFactory) { 
		this.queryFactory = queryFactory; 
	}
	 
	@Override
	public List<Token> findAllValidTokenByUserId(@NonNull String userName) {


		
		  QToken t = QToken.token1;
		  
		  JPAQuery<Tuple> query = queryFactory .select(t.id, t.token, t.tokenType,
		  t.expired, t.revoked, t.userName) .from(t) .where( t.userName.eq(userName));
		  
		  return query.fetch() .stream().map(tuple -> Token.builder()
		  .id(tuple.get(t.id)) .token(tuple.get(t.token))
		  .tokenType(tuple.get(t.tokenType)) .expired(tuple.get(t.expired))
		  .revoked(tuple.get(t.revoked)) .userName(tuple.get(t.userName)) .build()
		  ).toList();
		 
		
		
		 

	}

}
