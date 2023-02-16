package jointscamp.be.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jointscamp.be.entity.User;
import jointscamp.be.util.BcryptUtil;
import org.hibernate.validator.constraints.Length;

public record RegisterUserDto(
        @NotNull(message = "email should not null")
        @NotBlank(message = "email should not blank")
        @Email(message = "should be an email")
        String email,

        @NotNull(message = "email should not null")
        @NotBlank(message = "email should not blank")
        @Length(min = 6, message = "password length should >= 6")
        String password,

        @NotNull(message = "email should not null")
        @NotBlank(message = "email should not blank")
        @Length(min = 6, message = "username length should >= 6")
        String username
) {
    public User transformIntoUser(){
        return User
                .builder()
                .email(this.email)
                .password(BcryptUtil.bcrypt().encode(this.password))
                .build();
    }
}
