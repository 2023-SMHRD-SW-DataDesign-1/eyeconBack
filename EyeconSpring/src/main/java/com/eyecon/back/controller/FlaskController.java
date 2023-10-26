package com.eyecon.back.controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyecon.back.dto.StoreDTO;
import com.eyecon.back.entity.Salesarea;
import com.eyecon.back.method.TokenToEmail;
import com.eyecon.back.service.FlaskService;
import com.eyecon.back.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins ={"http://localhost:3000/", "http://localhost:5000/"}, allowCredentials = "true")
@RequiredArgsConstructor
@RequestMapping("/flask")
public class FlaskController {

	private final FlaskService flaskService;
	
	// gpt와 채팅전 gpt에 데이터 공급 요청
	@RequestMapping("/callData")
	public Salesarea callData(@CookieValue String accessToken) {
		
		System.out.println("FlaskController.callData");
		
		// 토큰에서 이메일 추출
		TokenToEmail tokenToEmail = new TokenToEmail();
		String email=tokenToEmail.getEmailFromToken(accessToken);
		System.out.println("이메일 :"+email);
		
		
		StoreDTO storeDTO = new StoreDTO();
		storeDTO.setEmail(email);
		flaskService.callData(storeDTO);
		
		
		
		
		return null;
	}
	
}
