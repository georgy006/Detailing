package com.example.detailing.services.jwt;

import com.example.detailing.persistence.models.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "MySuperSecretKeyForJwtMySuperSecretKeyForJwt"; // Должно быть 256 бит!

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 час
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, Users user) {
        String extractedUsername = extractUsername(token);
        boolean tokenValid = extractedUsername.equals(user.getEmail()) && !isTokenExpired(token);

        System.out.println("🔍 Проверка токена:");
        System.out.println(" - Извлечённый username: " + extractedUsername);
        System.out.println(" - Ожидаемый username: " + user.getEmail());
        System.out.println(" - Токен истёк? " + isTokenExpired(token));
        System.out.println(" - Токен валиден? " + tokenValid);

        return tokenValid;
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }
}
