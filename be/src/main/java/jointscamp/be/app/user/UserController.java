package jointscamp.be.app.user;

import jakarta.validation.Valid;
import jointscamp.be.dto.user.LoginUserDto;
import jointscamp.be.dto.user.RegisterUserDto;
import jointscamp.be.exception.UserExistException;
import jointscamp.be.exception.UserNotAuthenticatedException;
import jointscamp.be.exception.UserNotFoundException;
import jointscamp.be.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Response> createUser(@RequestBody @Valid RegisterUserDto dto) throws UserExistException {
        return this.service.createUser(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody @Valid LoginUserDto dto) throws UserNotFoundException, UserNotAuthenticatedException {
        return this.service.loginUser(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getUser(@PathVariable("id") Long id) throws UserNotFoundException {
        return this.service.getUserById(id);
    }
}
