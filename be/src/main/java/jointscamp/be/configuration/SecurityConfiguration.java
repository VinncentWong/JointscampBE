package jointscamp.be.configuration;

import jointscamp.be.security.filter.JwtFilter;
import jointscamp.be.security.manager.JwtManager;
import jointscamp.be.security.provider.JwtProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain security(HttpSecurity http) throws Exception{
        return http
                .csrf().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/produk/**").authenticated()
                .requestMatchers("/user/create", "/user/login", "/user/get/{id}").permitAll()
                .requestMatchers("/user/**").authenticated()
                .requestMatchers("/like/**").authenticated()
                .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public JwtProvider jwtProvider(){
        return new JwtProvider();
    }

    @Bean
    public JwtManager jwtManager(){
        return new JwtManager(jwtProvider());
    }
    @Bean
    public JwtFilter jwtFilter(){
        return new JwtFilter(jwtManager());
    }
}
