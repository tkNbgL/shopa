package com.example.shopA.utils;

import com.example.shopA.service.Impls.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
//eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1dGt1QGdtYWlsLmNvbSIsImlhdCI6MTYwOTk1MDk2NywiZXhwIjo2NzkzOTUwOTY3fQ.Z4H9jL_3GDQU91CcIFLKWwsbk6uWZx45ac8H5qkOq4gYDTOEnwdHD7n5A7MWWgS--N3J_bgK_K6blELPd6n02w
//5ff5e6e93342f900025e8a87
import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${shopa.app.jwtSecret}")
    private String jwtSecret; //TODO read it later from properties file
    @Value("${shopa.app.jwtExpirationMs}")
    private Long jwtExpirationTime;

    public String generateJwtToken(Authentication authentication) throws ParseException {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userDetails.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(jwtExpirationTime).toInstant()))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
