package com.forohub.app.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenService {

    @Value("${security.jwt.secret:change-me}")
    private String secret;

    @Value("${security.jwt.expiry-hours:24}")
    private long expiryHours;

    public String generateToken(String username) {
        Algorithm alg = Algorithm.HMAC256(secret);
        Instant now = Instant.now();
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(now.plus(expiryHours, ChronoUnit.HOURS)))
                .withIssuer("ForoHub")
                .sign(alg);
    }

    public String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isValid(String token, UserDetails user) {
        try {
            Algorithm alg = Algorithm.HMAC256(secret);
            DecodedJWT jwt = JWT.require(alg).withIssuer("ForoHub").build().verify(token);
            return jwt.getSubject().equals(user.getUsername()) && jwt.getExpiresAt().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
