package com.eyecon.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyecon.back.entity.Coin;
import com.eyecon.back.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	List<Payment> findByEmail(String email);
	
}
