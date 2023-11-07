package com.eyecon.back.service;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import com.eyecon.back.configuration.JwtService;
import com.eyecon.back.entity.Token;
import com.eyecon.back.repository.TokenRepository;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

	private final TokenRepository tokenRepository;
    private final JwtService jwtService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) {
		/*
		 * final String authHeader = request.getHeader("Authorization"); final String
		 * token; if (authHeader == null || !authHeader.startsWith("Bearer ")) { return;
		 * } token = authHeader.substring(7);
		 */
    	
    	Cookie[] cookies = request.getCookies();   	
    	String token = null;
    	
    	if (cookies != null) {
    	    token = Arrays.stream(cookies)
    	            .filter(c -> c.getName().equals("refreshToken"))
    	            .findFirst().map(Cookie::getValue)
    	            .orElse(null);
    	}else {
    		
    	}
    	
    	System.out.println("====== LogoutService =======");
		System.out.println("refreshToken : "+ token);
		
        if (!StringUtils.isEmpty(token)) {
            final String userEmail = jwtService.extractUsername(token);
            revokeAllUserTokens(userEmail);
        }
        Cookie accessTokenCookie = new Cookie("accessToken", null);
        
        accessTokenCookie.setDomain("3.36.133.196");
        accessTokenCookie.setSecure(true);
        accessTokenCookie.setMaxAge(0); // 즉시 만료
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setPath("/"); // 쿠키 Path 설정

        Cookie refreshTokenCookie = new Cookie("refreshToken", null);
        refreshTokenCookie.setDomain("3.36.133.196");
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setMaxAge(0); // 즉시 만료
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/"); // 쿠키 Path 설정

        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);
    }

    private void revokeAllUserTokens(String username) {
        List<Token> validTokens = tokenRepository.findAllValidTokenByUserId(username);
        if (!validTokens.isEmpty()) {
            validTokens.forEach(t -> {
                t.setExpired(true);
                t.setRevoked(true);
            });
            tokenRepository.saveAll(validTokens);
        }
    }

}
