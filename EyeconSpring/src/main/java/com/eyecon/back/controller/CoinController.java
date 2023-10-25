package com.eyecon.back.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyecon.back.dto.UserDTO;
import com.eyecon.back.method.TokenToEmail;
import com.eyecon.back.service.AuthService;
import com.eyecon.back.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/coin")
public class CoinController {

	private final UserService userService;
	
	
	
	
	
	// 서비스 이용시 코인 차감 메소드
	@RequestMapping("/removeCoin")
	public int removeCoin(@CookieValue String accessToken) {
		
		System.out.println("UserController.removeCoin");

		
		
		System.out.println("accessToken : "+accessToken);
		TokenToEmail tokenToEmail = new TokenToEmail();
		String email=tokenToEmail.getEmailFromToken(accessToken);
		
		UserDTO userDTO =new UserDTO();
		userDTO.setEmail(email);
		
        System.out.println("이에일 : " + userDTO.getEmail());
        int updatedCoin = userService.removeCoin(userDTO);
		
		System.out.println(updatedCoin);
		
		return updatedCoin;
	}
	
	
	
}
