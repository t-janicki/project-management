package com.auth.service.impl;

import com.auth.security.TokenProvider;
import com.auth.service.AuthService;
import com.utility.web.request.user.LoginRequest;
import com.utility.web.response.user.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private TokenProvider tokenProvider;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImpl(TokenProvider tokenProvider,
                           AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);

        return new AuthResponse(token);
    }

    public Long getUserIdFromToken(String token) {
        return tokenProvider.getUserIdFromToken(token);
    }

    public String createPasswordResetToken(String email) {
        return tokenProvider.createPasswordResetToken(email);
    }

    public Boolean validateToken(String token) {
        return tokenProvider.validateToken(token);
    }
}
