package jointscamp.be.interceptor;

import jointscamp.be.exception.UserExistException;
import jointscamp.be.exception.UserNotAuthenticatedException;
import jointscamp.be.exception.UserNotFoundException;
import jointscamp.be.util.Response;
import jointscamp.be.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class Interceptor {

    private final ResponseUtil util;

    public Interceptor(ResponseUtil util){
        this.util = util;
    }

    @ExceptionHandler(UserNotAuthenticatedException.class)
    public ResponseEntity<Response> handle(UserNotAuthenticatedException ex){
        return this.util.sendResponse(HttpStatus.UNAUTHORIZED, false, ex.getMessage(), null);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Response> handle(UserNotFoundException ex){
        return this.util.sendResponse(HttpStatus.INTERNAL_SERVER_ERROR, false, ex.getMessage(), null);
    }

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<Response> handle(UserExistException ex){
        return this.util.sendResponse(HttpStatus.INTERNAL_SERVER_ERROR, false, ex.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handle(MethodArgumentNotValidException ex){
        List<String> messages = new ArrayList<>();
        ex.getAllErrors().forEach((e) -> {
            messages.add(e.getDefaultMessage());
        });
        return this.util.sendResponse(HttpStatus.UNAUTHORIZED, false, messages, null);
    }
}
