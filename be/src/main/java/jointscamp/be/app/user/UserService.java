package jointscamp.be.app.user;

import jointscamp.be.dto.user.LoginUserDto;
import jointscamp.be.dto.user.RegisterUserDto;
import jointscamp.be.dto.user.UpdateUserDto;
import jointscamp.be.entity.User;
import jointscamp.be.exception.user.UserExistException;
import jointscamp.be.exception.user.UserNotAuthenticatedException;
import jointscamp.be.exception.user.UserNotFoundException;
import jointscamp.be.mapper.user.UserMapper;
import jointscamp.be.util.BcryptUtil;
import jointscamp.be.util.JwtUtil;
import jointscamp.be.util.Response;
import jointscamp.be.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class UserService {

    @Autowired
    private final UserRepository userRepo;

    @Autowired
    private final ResponseUtil responseUtil;

    @Autowired
    private final JwtUtil jwt;

    public UserService(UserRepository userRepo, ResponseUtil responseUtil, JwtUtil jwt){
        this.userRepo = userRepo;
        this.responseUtil = responseUtil;
        this.jwt = jwt;
    }

    public ResponseEntity<Response> createUser(RegisterUserDto dto) throws UserExistException{
        User user = dto.transformIntoUser();
        var users = this.userRepo.getUserByEmail(dto.email());
        if(users.isPresent()){
            throw new UserExistException("email already exist in database");
        }
        var result = this.userRepo.save(user);
        return this.responseUtil.sendResponse(HttpStatus.CREATED, true, "success create user", result);
    }

    public ResponseEntity<Map<String, Object>> loginUser(LoginUserDto dto) throws UserNotFoundException, UserNotAuthenticatedException {
        var user = this.userRepo.getUserByEmail(dto.email()).orElseThrow(() -> new UserNotFoundException("user not found"));
        log.info("password matches = " + BcryptUtil.bcrypt().matches(dto.password(), user.getPassword()));
        if(BcryptUtil.bcrypt().matches(dto.password(), user.getPassword())){
            String tokenJwt = this.jwt.generateToken(user);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "login user is success");
            response.put("success", true);
            response.put("data", user);
            response.put("jwt", tokenJwt);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        throw new UserNotAuthenticatedException("user not authenticated");
    }

    public ResponseEntity<Response> getUserById(Long id) throws UserNotFoundException {
        var user = this.userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("user not found"));
        return this.responseUtil.sendResponse(HttpStatus.OK, true, "success get user", user);
    }

    public ResponseEntity<Response> getAllUser(){
        Iterable<User> users = this.userRepo.findAll();
        return this.responseUtil.sendResponse(HttpStatus.OK, true, "success get users", users);
    }

    public ResponseEntity<Response> updateUser(UpdateUserDto dto, Long userId) throws UserNotFoundException {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("user data doesn't exist"));
        UserMapper.INSTANCE.updateUserFromUpdateDto(dto, user);
        return this.responseUtil.sendResponse(HttpStatus.OK, true, "success update user data", null);
    }
}
