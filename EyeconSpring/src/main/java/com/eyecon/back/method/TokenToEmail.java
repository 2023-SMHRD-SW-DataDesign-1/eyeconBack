package com.eyecon.back.method;

import javax.crypto.SecretKey;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class TokenToEmail {

	public String getEmailFromToken(String accessToken) {
	    System.out.println("jwt 토큰 이메일로 변환");
	    String token = accessToken;


	        String key = "3498237402398709832750923759823509283750293875098237E3432423423A4A561221ABD2324325FGFGS3535";
	        SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
	        Claims claims = Jwts.parserBuilder()
	                            .setSigningKey(secret)
	                            .build()
	                            .parseClaimsJws(token)
	                            .getBody();
	        return claims.getSubject();
	    
}
}