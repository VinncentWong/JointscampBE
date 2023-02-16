package jointscamp.be.security.authentication.util;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

public class BcryptUtil {

    public static BCryptPasswordEncoder bcrypt(){
        return new BCryptPasswordEncoder();
    }
}
