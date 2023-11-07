package com.eyecon.back.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyecon.back.dto.ResultDTO;
import com.eyecon.back.dto.StoreDTO;
import com.eyecon.back.entity.Result;
import com.eyecon.back.entity.Store;
import com.eyecon.back.method.TokenToEmail;
import com.eyecon.back.service.StoreService;
import com.eyecon.back.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/store")
@CrossOrigin(origins ={"http://localhost:3000","http://3.36.133.196:3000","https://3.36.133.196:3000","https://eyecon.site"})
@RequiredArgsConstructor
public class StoreController {

	private final StoreService storeService;
	
	@RequestMapping("/findStore")
	public List findStore(@CookieValue String accessToken) {
		
		System.out.println("StoreController.findStore");
		
		//토큰에서 이메일 추출
		StoreDTO storeDTO = new StoreDTO();
		TokenToEmail tokenToEmail = new TokenToEmail();
		String email=tokenToEmail.getEmailFromToken(accessToken);
		System.out.println("이메일 :"+email);
		
		storeDTO.setEmail(email);
//		Result result = flaskService.printImg(resultDTO); // 가장 최근 이미지들만 부를때
//		System.out.println("result : "+ result);
		
		List<Store> result =storeService.findStore(storeDTO); 
//		List<String> result =storeService.findStore(storeDTO); 
		
		return result;
	}
	
	
	
}
