package jointscamp.be.security.manager;

import jointscamp.be.security.provider.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JwtManager implements AuthenticationManager {

    private final JwtProvider provider;

    @Autowired
    public JwtManager(JwtProvider provider){
        this.provider = provider;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return provider.authenticate(authentication);
    }
}
