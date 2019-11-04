package com.auth.security;

import com.utility.exception.UnauthorizedException;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenProvider.class);

    private AuthProperties authProperties;

    public TokenProvider(AuthProperties authProperties) {
        this.authProperties = authProperties;
    }

    public String createToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + authProperties.getAuth().getTokenExpirationMsec());

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, authProperties.getAuth().getTokenSecret())
                .compact();
    }

    public String createPasswordResetToken(String email) {
        Date now = new Date();

        Date expiryDate = new Date(now.getTime() + authProperties.getAuth().getPasswordTokenExpirationMsec());

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, authProperties.getAuth().getTokenSecret())
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(authProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(authProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {

            LOGGER.error("Invalid JWT signature");
            throw new UnauthorizedException("Unauthorized");

        } catch (MalformedJwtException ex) {

            LOGGER.error("Invalid JWT token");
            throw new UnauthorizedException("Unauthorized");

        } catch (ExpiredJwtException ex) {

            LOGGER.error("Expired JWT token");
            throw new UnauthorizedException("Unauthorized");

        } catch (UnsupportedJwtException ex) {

            LOGGER.error("Unsupported JWT token");
            throw new UnauthorizedException("Unauthorized");


        } catch (IllegalArgumentException ex) {
            LOGGER.error("JWT claims string is empty.");
            throw new UnauthorizedException("Unauthorized");
        }
    }
}