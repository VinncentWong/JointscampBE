package jointscamp.be.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtUtil {

    @Value("secret_key")
    private String secret_key;

    public String generateToken(Extractable data){
        return Jwts.builder()
                .addClaims(Map.of(
                        "id", data.getId(),
                        "username", data.getUsername(),
                        "createdAt", data.getCreatedAt()
                ))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret_key)), SignatureAlgorithm.HS256)
                .compact();
    }
}
