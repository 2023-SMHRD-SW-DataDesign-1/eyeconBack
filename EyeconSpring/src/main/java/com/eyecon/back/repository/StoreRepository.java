package com.eyecon.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eyecon.back.entity.Result;
import com.eyecon.back.entity.Store;
import java.util.List;



//@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

	Optional<Store> findByEmail(String email);
	
//	@EntityGraph(attributePaths = {"store"})
    List<Store> findAllByEmail(String email);
    
    List<String> findStoreNameByEmail(String email);
}
