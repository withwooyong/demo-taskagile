package com.demo.demotaskagile.domain.common.security;

import com.demo.demotaskagile.domain.model.user.UserId;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Log4j2
@Component
public class TokenManager {

    private final Key secretKey;

    // docker run -d --name rabbitmq -p 5672:5672 -p 30000:15672 --restart=unless-stopped rabbitmq:management
    public TokenManager(@Value("${app.token-secret-key}") String secretKey) {
        log.info("secretKey={}", secretKey);
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    /**
     * Generate a JWT with user's id as its subject
     *
     * @param userId the id of the user
     * @return a JWT value
     */
    public String jwt(UserId userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId.value()))
                .signWith(SignatureAlgorithm.RS256, secretKey).compact();
//                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    /**
     * Get user id out of a JWT value
     *
     * @param jws the jwt string
     * @return user id
     */
    public UserId verifyJwt(String jws) {
        String userIdValue = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jws).getBody().getSubject();
        return new UserId(Long.parseLong(userIdValue));
    }
}
