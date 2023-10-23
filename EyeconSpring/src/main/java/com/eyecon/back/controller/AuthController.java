package com.eyecon.back.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
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
import com.google.common.net.HttpHeaders;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/auth")
public class AuthController {
	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest login) {
        User user = User.builder()
            .email(login.email()) 
            .pw(login.pw())
            .build(); 
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        AuthVO authVo = authService.authenticate(user);
        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", authVo.refreshToken())
            .httpOnly(true)
            .secure(true)
            .path("/")
            .maxAge(604800)
            .domain("localhost")
            .build();
        ResponseCookie accessCookie = ResponseCookie.from("accessToken", authVo.accessToken())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(1800)
                .domain("localhost")
                .build();
        System.out.println("refresh 발급 : " +refreshCookie.toString());
        System.out.println("access 발급 : " +accessCookie.toString());
        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
            .header(HttpHeaders.SET_COOKIE, accessCookie.toString())
            .body(null);
    
		// authority : 회원가입 , 인가
		// authentication : 로그인, 인증
		// 위에 2개를 합친걸 auth
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<AuthResponse> refreshToken(@CookieValue String refreshToken, HttpServletResponse response) throws IOException {
        String newAccessToken = "";
        java.util.Optional<String> refreshedAccessToken = authService.refreshToken(refreshToken, response);
        if (refreshedAccessToken.isPresent()) {
            newAccessToken = refreshedAccessToken.get();
        } 
        return ResponseEntity.ok()
            .body(new AuthResponse(newAccessToken));
    }
	
}

