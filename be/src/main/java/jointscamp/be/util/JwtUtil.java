package jointscamp.be.security.authentication.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${SECRET_KEY}")
    private String secret_key;

    public String generateToken(Extractable data){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", data.getId());
        claims.put("username", data.getUsername());
        claims.put("createdAt", data.getCreatedAt());
        return Jwts.builder()
                .addClaims(claims)
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret_key)), SignatureAlgorithm.HS256)
                .compact();
    }
}
