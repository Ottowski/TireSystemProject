package com.example.Grupp9.JwtConfig;


import com.example.Grupp9.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class JwtUtil {

    private static final String SECRET_KEY = "secrets_are_not_meant_to_be_shared_just_like_this";


    public String issueToken(String subject) {
        return issueTokens(subject, Map.of());
    }


    //    It allows us to issue a token with a subject and a set of claims.
    public String issueToken(String subject, String... scoops) {
        return issueTokens(subject, Map.of("scoops", scoops));
    }

    public String generateToken(UserDto userDto, List<String> roles) {
        return issueTokens(userDto.getUsername(), Map.of("roles", roles));
    }

    private String issueTokens(String subject, Map<String, Object> claims) {
        System.out.println("subject: → " + subject);
        System.out.println("claims: ✊ " + claims);
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(4, DAYS))).signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }



    public String getSubject(String token) {
        return getClaims(token).getSubject();
    }

    //    Extracts the subject from the token.
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //    Generates a key for the token.
    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }


    //    Validates the token and checks if the subject matches the username in the UserDetails object.
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getSubject(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }


    //    Checks if the token is expired.
    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(Date.from(Instant.now()));

    }
}
