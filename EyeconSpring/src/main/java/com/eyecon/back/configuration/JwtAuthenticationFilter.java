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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
            FilterChain filterChain) throws ServletException, IOException, UsernameNotFoundException {
		/* final String authHeader = request.getHeader("Authorization"); */
    	// 쿠키에서 accessToken을 가져옵니다.
    	Cookie[] cookies = request.getCookies();   	
    	String accessToken = null;
    	String refreshToken = null;
    	if (cookies != null) {
    		accessToken = Arrays.stream(cookies)
    	            .filter(c -> c.getName().equals("accessToken"))
    	            .findFirst().map(Cookie::getValue)
    	            .orElse(null);
    		refreshToken = Arrays.stream(cookies)
    	            .filter(c -> c.getName().equals("refreshToken"))
    	            .findFirst().map(Cookie::getValue)
    	            .orElse(null);
    		
    	}
		System.out.println("JwtAuthenticationFilter에서 출력중 accesstoken : "+ accessToken);
		System.out.println("JwtAuthenticationFilter에서 출력중 refreshToken : "+ refreshToken);
		
        String userEmail = null; // username
        
        // access + refresh null 이면? 즉 처음 로그인한 상태 
		if (accessToken == null && refreshToken == null) {
            filterChain.doFilter(request, response);
            return;
        }else if(accessToken == null && refreshToken != null) {
        	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is expired");
        	return;
        }
		
		try {
		    userEmail = jwtService.extractUsername(accessToken);
		} catch (io.jsonwebtoken.ExpiredJwtException e) {
			System.out.println("catch문 왔음");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is expired");
            return;
			
		}
        
        // SecurityContext에 인증 정보가 없고 사용자 이름이 null이 아닌 경우
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        	System.out.println("맨위 if문 왔음");
        	try {
        		UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
        		//boolean isTokenValid = tokenRepository.findByToken(jwt).map(t -> !t.isExpired() && !t.isRevoked()).orElse(false);
        		if (jwtService.isTokenValid(accessToken, userDetails))/* && isTokenValid)*/ {
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
        	} catch (UsernameNotFoundException e) {
            	// 사용자가 존재하지 않으면 404 에러 반환
        		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The user does not exist");
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
