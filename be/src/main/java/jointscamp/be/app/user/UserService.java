package jointscamp.be.app.user;

import jointscamp.be.dto.user.LoginUserDto;
import jointscamp.be.dto.user.RegisterUserDto;
import jointscamp.be.entity.User;
import jointscamp.be.exception.UserExistException;
import jointscamp.be.exception.UserNotAuthenticatedException;
import jointscamp.be.exception.UserNotFoundException;
import jointscamp.be.util.BcryptUtil;
import jointscamp.be.util.JwtUtil;
import jointscamp.be.util.Response;
import jointscamp.be.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
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
            throw new UserExistException("email sudah terdaftar di database");
        }
        var result = this.userRepo.save(user);
        return this.responseUtil.sendResponse(HttpStatus.CREATED, true, "success create user", result);
    }

    public ResponseEntity<Map<String, Object>> loginUser(LoginUserDto dto) throws UserNotFoundException, UserNotAuthenticatedException {
        var user = this.userRepo.getUserByEmail(dto.email()).orElseThrow(() -> new UserNotFoundException("data user tidak ditemukan"));
        if(BcryptUtil.bcrypt().matches(dto.password(), user.getPassword())){
            String tokenJwt = this.jwt.generateToken(user);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "login user is success");
            response.put("success", true);
            response.put("data", user);
            response.put("jwt", tokenJwt);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        throw new UserNotAuthenticatedException("user tidak terautentikasi");
    }
}
