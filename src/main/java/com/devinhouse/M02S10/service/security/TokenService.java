package com.devinhouse.M02S10.service.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.devinhouse.M02S10.model.Usuario;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class TokenService {

    @Value("${secret.key}")
    private String secret;

    public String obterToken(String bearerToken) {
        final String bearer = "Bearer ";
        if (bearerToken == null || !bearerToken.startsWith(bearer))
            throw new JWTVerificationException("Invalid Authorization Header");
        return bearerToken.substring(bearer.length());
    }

    public String obterSubject(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);  // throws JWTVerificationException if not valid
        return decodedJWT.getSubject();
    }

    public String gerarToken(Usuario user) {
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        Instant expiration = gerarExpiracao(10);  // expires in 10 min
        String token = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(expiration)
                .withIssuer("M02S10-API")
                .withClaim("roles", user.getRole().name())
                .sign(algorithm);
        return token;
    }

    private Instant gerarExpiracao(int minutes) {
        return LocalDateTime.now().plusMinutes(minutes).atZone(ZoneId.systemDefault()).toInstant();
    }

//    public DecodedJWT getDecodedTokenFrom(String token) {
//        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
//        JWTVerifier verifier = JWT.require(algorithm).build();
//        DecodedJWT decodedJWT = verifier.verify(token);
//        return decodedJWT;
//    }

//    public String generateAccessToken(AppUser user) {
//        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
//        String accessToken = JWT.create()
//                .withSubject(user.getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))  // expires in 10 min
//                .withIssuer("Books-API")
//                .withClaim("roles", user.getRole().name())
//                .sign(algorithm);
//        return accessToken;
//    }
//
//    public String generateRefreshToken(AppUser user) {
//        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
//        String refreshToken = JWT.create()
//                .withSubject(user.getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))  // expires in 60 min
//                .withIssuer("Books-API")
//                .sign(algorithm);
//        return refreshToken;
//    }

}
