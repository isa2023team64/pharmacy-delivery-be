package com.isa2023team64.pharmacydeliverybe.config.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.model.Role;
import com.isa2023team64.pharmacydeliverybe.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	@Value("pharmacy-delivery-be")
	private String APP_NAME;

	private static final String AUDIENCE_WEB = "web";

    private static final String SECRET_KEY = "1E9C4A0D68F73B8C21A6D05B8C9F2E7A8B2D1C4A9F3C6A7F0B2E5D8C1A4F7E2B5D8C1A9F3E6A7F0B2E5D8C1A4F7E2";
    private static final Double hoursValid = 0.5;
    private static final Integer millisecondsValid = (int)(1000 * 60 * 60 * hoursValid);

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(User user) {
        List<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        claims.put("id", user.getId());
        return generateToken(claims, user);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
            .setIssuer(APP_NAME)
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setAudience(AUDIENCE_WEB)
            .setExpiration(new Date(System.currentTimeMillis() + millisecondsValid))
            .signWith(getSignInKey(), SignatureAlgorithm.HS512)
            .compact();
    }

    public Integer getExpirationDuration() {
        return millisecondsValid;
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(getSignInKey()).build();
        return jwtParser.parseClaimsJws(token).getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
}
