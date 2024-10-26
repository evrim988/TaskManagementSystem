package org.example.taskmanagementsystemproject.utility;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class JwtManager {

    //ilk iki değer application.yml a alınacak.
    private String SecretKey = "Aa157bd";
    private String Issuer = "TaskManagementSystemProject";
    private final Long ExDate = 1000L * 60 * 10; //10 dakika

    public String createToken(Long authId) {
        Date createdDate = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(System.currentTimeMillis() + ExDate);

        Algorithm algorithm = Algorithm.HMAC256(SecretKey);

        String token = JWT.create()
                .withAudience()
                .withIssuer(Issuer)
                .withIssuedAt(createdDate)
                .withExpiresAt(expirationDate)
                .withClaim("authId", authId)
                .withClaim("key", "TS_TMSP")
                .sign(algorithm);

        return token;
    }

    public Optional<Long> validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SecretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (Objects.isNull(decodedJWT)) {
                return Optional.empty();
            }
            Long authId = decodedJWT.getClaim("authId").asLong();
            return Optional.of(authId);

        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
