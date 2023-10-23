package com.eyecon.back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyecon.back.entity.Token;


public interface TokenRepository extends JpaRepository<Token, Long>, TokenRepositoryCustom {
    Optional<Token> findByToken(String token);
    
    List<Token> findByTokenAndUserNameAndRevoked(String refreshToken, String userEmail, boolean revoked);
   
}
