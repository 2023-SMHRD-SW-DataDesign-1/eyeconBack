package com.eyecon.back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyecon.back.entity.Tokens;


public interface TokenRepository extends JpaRepository<Tokens, Long>, TokenRepositoryCustom {
    Optional<Tokens> findByToken(String token);
    
    List<Tokens> findByTokenAndUserNameAndRevoked(String refreshToken, String userEmail, boolean revoked);
   
}
