package com.eyecon.back.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.eyecon.back.configuration.JwtService;
import com.eyecon.back.dto.AuthVO;
import com.eyecon.back.entity.TokenType;
import com.eyecon.back.entity.Token;
import com.eyecon.back.entity.User;
import com.eyecon.back.repository.TokenRepository;
import com.eyecon.back.repository.UserRepository;

import lombok.RequiredArgsConstructor;




@Service
@RequiredArgsConstructor
public class AuthService {
	private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    // public User authenticate(User user) {
    public AuthVO authenticate(User user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        //saveToken(user, jwtToken); access 토큰 저장안하기로 함
        // refresh 토큰 db저장
        saveToken(user, refreshToken);
        return new AuthVO( jwtToken, refreshToken);
    }

    private void revokeAllUserTokens(User user) {
        List<Token> validTokens = tokenRepository.findAllValidTokenByUserId(user.getEmail());
        if (!validTokens.isEmpty()) {
            validTokens.forEach( t-> {
                t.setExpired(true);
                t.setRevoked(true);
                tokenRepository.save(t);
            });
        }
    }
    private void saveToken (User user, String jwtToken) {
        Token token = Token.builder()
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .userName(user.getUsername())
        .build();
        tokenRepository.save(token);
    }

    public Optional<String> refreshToken(String refreshToken, HttpServletResponse response) throws IOException {
    	String accessToken = null;
        if (!ObjectUtils.isEmpty(refreshToken)) {
            final var userEmail = jwtService.extractUsername(refreshToken);
            if (userEmail != null) {
                var user = userRepository.findByEmail(userEmail);
                List<Token> validRefreshTokens = tokenRepository.findByTokenAndUserNameAndRevoked(refreshToken, userEmail, false);
                if (user.isPresent() && validRefreshTokens.size() > 0 && jwtService.isTokenValid(refreshToken, user.get())) {
                    accessToken = jwtService.generateToken(user.get());
                    saveToken(user.get(), accessToken);
                } 
            }
        }
        return accessToken == null ? Optional.empty() : Optional.of(accessToken);
    }
}
