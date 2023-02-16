package jointscamp.be.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jointscamp.be.security.authentication.JwtAuthentication;
import jointscamp.be.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.security.sasl.AuthenticationException;
import java.io.IOException;


public class JwtFilter extends OncePerRequestFilter {

    private final AuthenticationManager manager;

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public JwtFilter(AuthenticationManager manager){
        this.manager = manager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearer = request.getHeader("Authorization");
        if(bearer == null){
            filterChain.doFilter(request, response);
            return;
        }
        if(!bearer.startsWith("Bearer ")){
            this.setErrorResponse(response, HttpStatus.BAD_REQUEST, "wrong authorization header value", false);
        } else {
            // slice
            String token = bearer.substring(7);
            Authentication auth = this.manager.authenticate(new JwtAuthentication(token, null));
            if(auth.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(auth);
                filterChain.doFilter(request, response);
            } else{
                this.setErrorResponse(response, HttpStatus.FORBIDDEN, "invalid jwt token", false);
            }
        }
    }

    private void setErrorResponse(HttpServletResponse response, HttpStatus status, String message, boolean success) throws IOException {
        Response errResponse = Response
                .builder()
                .success(success)
                .data(null)
                .message(message)
                .build();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(status.value());
        response.getWriter().write(this.mapper.writeValueAsString(errResponse));
    }

}
