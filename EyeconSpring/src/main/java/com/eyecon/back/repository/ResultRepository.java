package com.eyecon.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eyecon.back.dto.ResultDTO;
import com.eyecon.back.entity.Result;
import java.util.List;




public interface ResultRepository extends JpaRepository<Result, Long>{

	

	List<Result> findByEmailOrderByIdDesc(String email);
	
	Optional<Result> findByEmail(String email);
	Optional<Result> findFirstByEmailOrderByIdDesc(String email);

}
