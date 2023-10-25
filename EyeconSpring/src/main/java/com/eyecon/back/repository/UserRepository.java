package com.eyecon.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eyecon.back.entity.User;
import java.util.List;



public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); 
    Optional<User> findByEmailAndPw(String email, String pw);
   

   
}
