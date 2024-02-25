package com.ecom.userService.service;

import com.ecom.userService.auth.JwtService;
import com.ecom.userService.dto.AuthRequest;
import com.ecom.userService.dto.AuthResponse;
import com.ecom.userService.dto.TokenType;
import com.ecom.userService.entity.TokenEntity;
import com.ecom.userService.entity.UserEntity;
import com.ecom.userService.repository.TokenRepository;
import com.ecom.userService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse authenticate(AuthRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
        var user = repository.findByEmailId(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    private void saveUserToken(UserEntity user, String jwtToken) {
        var token = TokenEntity.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .userId(user.getId())
                .build();
        tokenRepository.save(token);
    }
    private void revokeAllUserTokens(UserEntity user) {
        //var validUserTokens = tokenRepository.findAllValidTokenByUserId(user.getUserId());
        var validUserTokens = tokenRepository.findByUserEmailId(user.getEmailId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
