package com.eyecon.back.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyecon.back.entity.Coin;
import com.eyecon.back.repository.CoinRepository;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/coin")
@RequiredArgsConstructor
public class CoinController {
	
	private final CoinRepository coinRepository;
	
	// 모든 코인 상품 return
	@GetMapping("")
	public List<Coin> selectAllCoin() {
		
		List<Coin> coinList = coinRepository.findAll();
		
		System.out.println(coinList.get(0).getCoinName());
		return coinRepository.findAll();
	}
}
