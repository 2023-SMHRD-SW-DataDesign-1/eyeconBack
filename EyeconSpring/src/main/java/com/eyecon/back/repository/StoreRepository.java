package com.eyecon.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eyecon.back.entity.Store;
import java.util.List;



@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

	Optional<Store> findByUser_Email(String email);
	
}
