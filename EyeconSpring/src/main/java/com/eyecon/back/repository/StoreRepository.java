package com.eyecon.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eyecon.back.entity.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

	
	
}
