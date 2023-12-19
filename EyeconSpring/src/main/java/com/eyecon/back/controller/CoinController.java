package com.eyecon.back.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyecon.back.dto.UserDTO;
import com.eyecon.back.entity.Coin;
import com.eyecon.back.method.TokenToEmail;
import com.eyecon.back.repository.CoinRepository;
import com.eyecon.back.service.AuthService;
import com.eyecon.back.service.CoinService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000","http://43.203.38.24:3000","https://43.203.38.24:3000","https://eyecon.site","http://eyecon.site","https://eyecon.site:3000","http://eyecon.site:3000"}, allowCredentials = "true")
@RequestMapping("/coin")
public class CoinController {

	private final CoinService coinService;
	private final CoinRepository coinRepository;

	// 코인 조회 함수 (비동기?)
	@RequestMapping("/findCoin")
	public int findCoin(@CookieValue String accessToken) {

		System.out.println("CoinController.findCoin");
		TokenToEmail tokenToEmail = new TokenToEmail();
		String email = tokenToEmail.getEmailFromToken(accessToken);

		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(email);
		System.out.println("이에일 : " + userDTO.getEmail());
		int countedCoin = coinService.findCoin(userDTO);

		return countedCoin;// 임시
	}

	// 서비스 이용시 코인 차감 메소드
	@RequestMapping("/removeCoin")
	public int removeCoin(@CookieValue String accessToken) {

		System.out.println("CoinController.removeCoin");

		System.out.println("accessToken : " + accessToken);
		TokenToEmail tokenToEmail = new TokenToEmail();
		String email = tokenToEmail.getEmailFromToken(accessToken);

		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(email);

		System.out.println("이에일 : " + userDTO.getEmail());
		int updatedCoin = coinService.removeCoin(userDTO);

		System.out.println(updatedCoin);

		return updatedCoin;
	}

	// 모든 코인 상품 return
	@GetMapping("")
	public List<Coin> selectAllCoin() {

		List<Coin> coinList = coinRepository.findAll();

		System.out.println(coinList.get(0).getCoinName());
		return coinRepository.findAll();
	}

}
