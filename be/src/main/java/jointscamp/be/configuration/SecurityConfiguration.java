package jointscamp.be.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain security(HttpSecurity http) throws Exception{
        return http
                .csrf().disable()
                .httpBasic().disable()
                .build();
    }
}
