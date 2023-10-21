package com.eyecon.back.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eyecon.back.dto.UserDTO;
import com.eyecon.back.service.UserService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	
    @RequestMapping("/join.do")
    public String join(@RequestBody UserDTO userDTO) {
        System.out.println("UserController.join");
        System.out.println("userDTO : " + userDTO);
        userService.join(userDTO);
        return "login";
    }
}
