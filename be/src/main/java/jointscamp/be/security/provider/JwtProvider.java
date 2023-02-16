package jointscamp.be.security.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.SignatureException;
import jointscamp.be.security.authentication.JwtAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class JwtProvider implements AuthenticationProvider {

    @Value("${SECRET_KEY}")
    private String secretKey;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try{
            // Extract token
            if(authentication.getPrincipal() instanceof String token){
                var parser = Jwts
                        .parserBuilder()
                        .setSigningKey(Decoders.BASE64URL.decode(secretKey))
                        .build();
                var jwt = parser.parse(token);
                if(jwt.getBody() instanceof Claims c){
                    String role = (String)c.get("role");
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority(String.format("ROLE_%s", role)));
                    return new JwtAuthentication(token, null, authorities);
                } else {
                    return new JwtAuthentication(null, null);
                }
            } else {
                return new JwtAuthentication(null, null);
            }
        }
        catch(ExpiredJwtException ex){
            // Jwt was expired
            return new JwtAuthentication(null, null);
        }
        catch(MalformedJwtException ex){
            // Jwt was incorrectly constructed
            return new JwtAuthentication(null, null);
        }
        catch(SignatureException ex){
            // Invalid signature
            return new JwtAuthentication(null, null);
        }
        catch(IllegalArgumentException ex){
            // Wrong argument type(should not be a null)
            return new JwtAuthentication(null, null);
        }
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthentication.class);
    }
}
