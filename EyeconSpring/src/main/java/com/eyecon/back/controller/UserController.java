package com.eyecon.back.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eyecon.back.dto.UserDTO;
import com.eyecon.back.entity.Store;
import com.eyecon.back.service.UserService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	
    @RequestMapping("/join.do")
    public String join(@RequestBody UserDTO userDTO) {
        System.out.println("UserController.join");
        System.out.println("userDTO : " + userDTO);
 
        // Store 객체 생성 (service에서 storeDTO를 쓰면 에러나서 그냥 entity 썼음)
        Store store = new Store();

        userService.join(userDTO, store);
        return "login";
    }
}
