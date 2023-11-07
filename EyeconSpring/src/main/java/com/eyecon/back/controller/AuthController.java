package com.eyecon.back.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyecon.back.dto.AuthRequest;
import com.eyecon.back.dto.AuthResponse;
import com.eyecon.back.dto.AuthVO;
import com.eyecon.back.entity.User;
import com.eyecon.back.service.AuthService;
import com.eyecon.back.service.UserService;
import com.google.common.net.HttpHeaders;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000","http://13.124.30.27:3000"}, allowCredentials = "true")
@RequestMapping("/auth")
public class AuthController {
	private final AuthService authService;
	private final UserService userService;

	@PostMapping("/login")
	public ResponseEntity<String> authenticate(@RequestBody AuthRequest login) {
        User user = User.builder()
            .email(login.email()) 
            .pw(login.pw())
            .build();
        userService.findUser(user);
        AuthVO authVo = authService.authenticate(user);
        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", authVo.refreshToken())
            .httpOnly(true)
            .sameSite("None")
            .secure(true)
            .path("/")
            .maxAge(604800)
            .domain("localhost")
            .domain("13.124.30.27")
            .build();
        ResponseCookie accessCookie = ResponseCookie.from("accessToken", authVo.accessToken())
                .httpOnly(true)
                .sameSite("None")
                .secure(true)
                .path("/")
                .maxAge(1800)
                .domain("localhost")
                .domain("13.124.30.27")
                .build();
        System.out.println("refresh 발급 : " +refreshCookie.toString());
        System.out.println("access 발급 : " +accessCookie.toString());
        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
            .header(HttpHeaders.SET_COOKIE, accessCookie.toString())
            .body("Exist");
    
		// authority : 회원가입 , 인가
		// authentication : 로그인, 인증
		// 위에 2개를 합친걸 auth
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<AuthResponse> refreshToken(@CookieValue String refreshToken, HttpServletResponse response) throws IOException {
		System.out.println("/refresh 도착");
        String newAccessToken = "";
        java.util.Optional<String> refreshedAccessToken = authService.refreshToken(refreshToken, response);
        if (refreshedAccessToken.isPresent()) {
            newAccessToken = refreshedAccessToken.get();
        } 
        ResponseCookie accessCookie = ResponseCookie.from("accessToken", newAccessToken)
                .httpOnly(true)
                .sameSite("None")
                .secure(true)
                .path("/")
                .maxAge(1800)
                .domain("localhost")
                .domain("13.124.30.27")
                .build();
       System.out.println("재발급 된 refresh : " + newAccessToken);
        return ResponseEntity.ok()
        	.header(HttpHeaders.SET_COOKIE, accessCookie.toString())
            .body(null);
    }
	
}

