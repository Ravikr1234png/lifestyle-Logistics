package com.ravi.lifestyle.auth.security;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtil {
  public static String generateToken(String subject, String secret, long ttlMillis) {
    SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    long now = System.currentTimeMillis();
    return Jwts.builder().setSubject(subject)
            .setIssuedAt(new Date(now))
            .setExpiration(new Date(now + ttlMillis))
            .signWith(key,
                    SignatureAlgorithm.HS256).compact();
  }
}
