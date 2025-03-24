package br.com.calculo_de_impostos.infra.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {
    private Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public JwtUtil(Key SECRET_KEY) {
        this.SECRET_KEY = SECRET_KEY;
    }

    public JwtUtil() {}

    public String generationToken(String userName, List<String> roles) {
        return Jwts.builder()
                .setSubject(userName)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SECRET_KEY)
                .compact();
    }

    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, ClaimsResolver<T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.resolve(claims);
    }

    public interface ClaimsResolver<T>{
        T resolve(Claims claims);
    }

    public Claims extractAllClaims(String token){
        JwtParser parser = Jwts.parser().setSigningKey(SECRET_KEY).build();
        return parser.parseClaimsJws(token).getBody();
    }

    public String getUserNameFromToken(String token){
        return extractAllClaims(token).getSubject();
    }

    public boolean validateToken(String token){
        return !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}