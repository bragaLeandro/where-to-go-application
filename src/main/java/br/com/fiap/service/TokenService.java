package br.com.fiap.service;

import br.com.fiap.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${security.jwt.secret}")
    private String secretKey;

    public String generateToken(User user) {
        return JWT.create()
                .withIssuer("User")
                .withSubject(user.getUsername())
                .withClaim("id", user.getId())
                .withExpiresAt(LocalDateTime.now()
                        .plusMinutes(30)
                        .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256(secretKey));
    }


    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256(secretKey))
                .withIssuer("User")
                .build().verify(token).getSubject();

    }
}
