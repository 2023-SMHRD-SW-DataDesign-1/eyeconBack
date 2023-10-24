package com.eyecon.back.method;

import javax.crypto.SecretKey;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class TokenToEmail {

    public String getEmailFromToken(HttpServletRequest request) {
        System.out.println("jwt 토큰 이메일로 변환");
    	Cookie[] cookies = request.getCookies();
        String token = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("access_token")) {
                token = cookie.getValue();
                break;
            }
        }

        if (token != null) {
            String key = "3498237402398709832750923759823509283750293875098237E3432423423A4A561221ABD2324325FGFGS3535";
            SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
            Claims claims = Jwts.parserBuilder()
                                .setSigningKey(secret)
                                .build()
                                .parseClaimsJws(token)
                                .getBody();
            return claims.getSubject();
        }

        return null;
    }
}
