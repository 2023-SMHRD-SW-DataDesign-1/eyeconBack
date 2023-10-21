package com.eyecon.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eyecon.back.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

    // 이메일로 회원 정보 조회 (select * from User where email=?)
    public Optional<User> findByEmail(String email);
	
}
