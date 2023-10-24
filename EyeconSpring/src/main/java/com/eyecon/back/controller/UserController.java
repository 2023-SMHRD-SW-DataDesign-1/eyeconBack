package com.eyecon.back.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.eyecon.back.dto.StoreDTO;
import com.eyecon.back.dto.UserDTO;
import com.eyecon.back.entity.Store;
import com.eyecon.back.service.UserService;

import lombok.RequiredArgsConstructor;


@RestController
@CrossOrigin(origins ="http://localhost:3000/")
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	
    @RequestMapping("/join")
    public String join(@RequestHeader StoreDTO storeDTO, UserDTO userDTO ) {
        System.out.println("UserController.join");
        System.out.println("userDTO : " + userDTO);
        System.out.println("storeDTO : " + storeDTO);
        
 
        // Store 객체 생성 (service에서 storeDTO를 쓰면 에러나서 그냥 entity 썼음)
        

        userService.join(userDTO, storeDTO);
        return "login";
    }
    
    
    @PostMapping("/checkEmail")
    public boolean checkEmail (@RequestBody UserDTO userDTO ) {
        System.out.println("UserController.checkEmail");
        System.out.println("userDTO : " + userDTO);
        
        
        return userService.checkEmail(userDTO.getEmail());
    }
    
	@RequestMapping("/removeCoin")
	public int removeCoin(@RequestBody String email) {
		// jwt 토큰 되면 토큰에서 이메일 추출하는 코드 써야함
		// ?????
		UserDTO userDTO = new UserDTO();
		System.out.println("paymentController.removeCoin");
		
        System.out.println("이에일 : " + email);
		userService.removeCoin(email);
		
		
		return userDTO.getCoin();
	}
    
    
    
    
}
