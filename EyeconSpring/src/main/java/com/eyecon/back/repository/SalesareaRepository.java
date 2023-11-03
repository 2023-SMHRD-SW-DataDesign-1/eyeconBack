package com.eyecon.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eyecon.back.entity.Salesarea;
import java.util.List;


public interface SalesareaRepository extends JpaRepository<Salesarea, Long> {

	
	@Query("SELECT s FROM Salesarea s WHERE (s.place LIKE %:dong% or s.place LIKE %:doro%) and s.category = :category")
	List<Salesarea> findPlaceContainingDongAndDoro(@Param("doro") String doro, @Param("dong") String dong ,@Param("category") String category);
	
	
}
