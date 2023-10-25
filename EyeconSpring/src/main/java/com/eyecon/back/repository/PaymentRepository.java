package com.eyecon.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyecon.back.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
}
