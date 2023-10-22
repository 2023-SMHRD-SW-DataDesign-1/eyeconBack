package com.eyecon.back.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import com.eyecon.back.configuration.JwtService;
import com.eyecon.back.entity.Tokens;
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
        final String authHeader = request.getHeader("Authorization");
        final String token;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        token = authHeader.substring(7);
        if (!StringUtils.isEmpty(token)) {
            final String userEmail = jwtService.extractUsername(token);
            revokeAllUserTokens(userEmail);
        }
    }

    private void revokeAllUserTokens(String username) {
        List<Tokens> validTokens = tokenRepository.findAllValidTokenByUserId(username);
        if (!validTokens.isEmpty()) {
            validTokens.forEach(t -> {
                t.setExpired(true);
                t.setRevoked(true);
            });
            tokenRepository.saveAll(validTokens);
        }
    }

}
