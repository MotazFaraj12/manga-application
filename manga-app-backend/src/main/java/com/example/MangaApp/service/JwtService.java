package com.example.MangaApp.service;

import com.example.MangaApp.model.role;
import com.example.MangaApp.security.model.AuthUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String secretKey = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    private static final long jwtExpiration = 86400000;
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String generateToken(Map<String, Object> extraClaims, AuthUser authUser) {
        return buildToken(extraClaims, authUser);
    }
    private String buildToken(Map<String, Object> extraClaims, AuthUser authUser) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(authUser.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean isTokenValid(String token, AuthUser authUser) {
        final String username = extractUsername(token);
        return (username.equals(authUser.getUsername())) && !isTokenExpired(token);
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public AuthUser GetAuthUser(String Token){

        Claims claims = extractAllClaims(Token);
        Long id = Long.valueOf(claims.get("id", Integer.class));
        String username = claims.get("username", String.class);
        String subject = claims.getSubject();
        role roles = role.valueOf(claims.get("role" , String.class));
        boolean isDeleted = claims.get("isDeleted" , Boolean.class);
        boolean isVerified = claims.get("isVerified" , Boolean.class);

        AuthUser.AuthUserBuilder authUser = AuthUser.builder();
        authUser.id(id);
        authUser.username(username);
        authUser.role(roles);
        authUser.email(subject);
        authUser.isDeleted(isDeleted);
        authUser.isVerified(isVerified);
        return authUser.build();
    }
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
