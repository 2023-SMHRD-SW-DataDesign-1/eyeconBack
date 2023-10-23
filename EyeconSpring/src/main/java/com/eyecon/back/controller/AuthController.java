package com.eyecon.back.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyecon.back.dto.AuthRequest;
import com.eyecon.back.dto.AuthResponse;
import com.eyecon.back.dto.AuthVO;
import com.eyecon.back.entity.User;
import com.google.common.net.HttpHeaders;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	private final com.eyecon.back.service.AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest login) {
        User user = User.builder()
            .email(login.email()) 
            .pw(login.password())
            .build(); 
        AuthVO authVo = authService.authenticate(user);

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", authVo.refreshToken())
            .httpOnly(true)
            .secure(true)
            .path("/")
            .maxAge(604800)
            .domain("localhost")
            .build();
        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())	
            .body(new AuthResponse(authVo.accessToken()));
    
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

