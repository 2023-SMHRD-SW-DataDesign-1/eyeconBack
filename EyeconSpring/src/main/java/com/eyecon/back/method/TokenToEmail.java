package com.eyecon.back.method;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class TokenToEmail {

	
	public String getEmailFromToken() {
		HttpServletRequest request = null ;
		
    Cookie[] cookies = request.getCookies();
    String token = null;
    for (Cookie cookie : cookies) {
        if (cookie.getName().equals("access_token")) {
            token = cookie.getValue();
            break;
        }
    }

    if (token != null) {
        Claims claims = Jwts.parser()
                            .setSigningKey("3498237402398709832750923759823509283750293875098237E3432423423A4A561221ABD2324325FGFGS3535")
                            .parseClaimsJws(token)
                            .getBody();
        return claims.getSubject();
    }

    return null;
	
	}
	
	
}
