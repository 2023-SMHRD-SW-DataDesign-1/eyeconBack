package com.eyecon.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyecon.back.entity.Result;
import java.util.List;


public interface ResultRepository extends JpaRepository<Result, Long>{

	
	List<Result> findByEmailOrderByIdDesc(String email);
	
}
