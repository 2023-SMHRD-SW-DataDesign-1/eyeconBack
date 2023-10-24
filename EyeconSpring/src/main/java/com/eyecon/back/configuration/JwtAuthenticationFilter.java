package com.eyecon.back.configuration;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.eyecon.back.repository.TokenRepository;



@Component

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;
    
    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService, TokenRepository tokenRepository) {
    	this.jwtService = jwtService;
    	this.tokenRepository = tokenRepository;
    	this.userDetailsService = userDetailsService;
    }
    
    // 인증에서 제외할 url
 	private static final List<String> EXCLUDE_URL =
 		Collections.unmodifiableList(
 			Arrays.asList(
 				"/auth/refresh"
 				
 			));

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
		/* final String authHeader = request.getHeader("Authorization"); */
    	// 쿠키에서 accessToken을 가져옵니다.
    	Cookie[] cookies = request.getCookies();   	
    	String token = null;
    	
    	if (cookies != null) {
    	    token = Arrays.stream(cookies)
    	            .filter(c -> c.getName().equals("accessToken"))
    	            .findFirst().map(Cookie::getValue)
    	            .orElse(null);
    	}else {
    		
    	}
		System.out.println("token : "+ token);
		/* final String jwt; */
        String userEmail = null; // username
        
		if (token == null /* || !token.startsWith("Bearer ") */) {
            filterChain.doFilter(request, response);
            return;
        }
		/* jwt = token.substring(7); */
		try {
		    userEmail = jwtService.extractUsername(token);
		} catch (io.jsonwebtoken.ExpiredJwtException e) {
			System.out.println("catch문 왔음");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is expired");
            return;
			
		}
        
        // SecurityContext에 인증 정보가 없고 사용자 이름이 null이 아닌 경우
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        	System.out.println("맨위 if문 왔음");
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
            //boolean isTokenValid = tokenRepository.findByToken(jwt).map(t -> !t.isExpired() && !t.isRevoked()).orElse(false);
            if (jwtService.isTokenValid(token, userDetails))/* && isTokenValid)*/ {
            	System.out.println("if문 왔음");
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null,
                                userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
            	// 토큰이 유호하지 않으면 401 에러를 반환
            	System.out.println("else문 왔음");
            	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is expired");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
    
    @Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return EXCLUDE_URL.stream().anyMatch(exclude -> exclude.equalsIgnoreCase(request.getServletPath()));
	}
    
    
}
