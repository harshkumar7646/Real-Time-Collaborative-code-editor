package com.example.learningSpring.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;



@Service
public class jwtService {
    private SecretKey key;
    private long ats;
    private String issuer;
    private long refreshToken;

    public jwtService(@Value("${jwt.secret}")String secret,@Value("${jwt.expiration}") long ats, @Value("${jwt.issuer}") String issuer,
                      @Value("${jwt.refresh-expiration}") long refreshToken) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.ats = ats;
        this.issuer = issuer;
        this.refreshToken = refreshToken;
    }

    public String generateAccessToken(UserDetails userDetails) {

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuer(issuer)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(
                        new Date(System.currentTimeMillis() + ats)
                )
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }
    public Claims parse(String token) {
            return Jwts.parser()
                    .verifyWith(key)
                    .requireIssuer(issuer)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

    }
}




