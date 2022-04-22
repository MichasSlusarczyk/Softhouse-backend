package pl.polsl.softhouse.components.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    //@Value("jwt_secret")
    private static final String SECRET = "asdfzxcv1234"; // TODO

    private static final String SUBJECT = "User Details";
    private static final String ISSUER = "softhouse";
    private static final String USERNAME = "username";

    private static final int SECONDS_VALID = 60 * 24 * 30;

    public String generateToken(String username) {
        Date currentTime = new Date(),
                validUntil = new Date(System.currentTimeMillis() + SECONDS_VALID * 1000);

        return JWT.create()
                .withSubject(SUBJECT)
                .withClaim(USERNAME, username)
                .withIssuedAt(currentTime)
                .withIssuer(ISSUER)
                .withExpiresAt(validUntil)
                .sign(Algorithm.HMAC256(SECRET));
    }

    public String validateAndGetUsername(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                .withSubject(SUBJECT)
                .withIssuer(ISSUER)
                .build();

        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim(USERNAME).asString();
    }
}
