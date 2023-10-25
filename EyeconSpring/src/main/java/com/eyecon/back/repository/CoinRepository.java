package com.eyecon.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyecon.back.entity.Coin;
import java.util.List;


public interface CoinRepository extends JpaRepository<Coin, Integer>{
	Coin findByPrice(int price);
}
