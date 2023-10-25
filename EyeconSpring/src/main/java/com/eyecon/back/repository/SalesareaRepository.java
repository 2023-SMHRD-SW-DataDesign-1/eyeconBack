package com.eyecon.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyecon.back.entity.Salesarea;
import java.util.List;


public interface SalesareaRepository extends JpaRepository<Salesarea, Long> {

	
	 Optional<Salesarea> findByPlaceContaining(String place);
	
	
}
