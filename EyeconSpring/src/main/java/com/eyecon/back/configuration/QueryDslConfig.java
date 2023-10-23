package com.eyecon.back.configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;



@Configuration
public class QueryDslConfig {
	 
//	  @PersistenceContext(unitName = "primaryEntityManager") 
	  @PersistenceContext
	  private EntityManager JpaTransactionManager;
//	  
//	  @PersistenceContext(unitName = "secondaryEntityManager") private
//	  EntityManager secondaryJpaTransactionManager;
//	 

	
	  @Bean 
	  public JPAQueryFactory JpaQueryFactory() { 
		  return new JPAQueryFactory(JpaTransactionManager); 
	  }
	  
//	  @Bean public JPAQueryFactory secondaryJpaQueryFactory() { return new
//	  JPAQueryFactory(secondaryJpaTransactionManager); }
//	 
}
