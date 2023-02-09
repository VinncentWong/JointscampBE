package jointscamp.be.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jointscamp.be.entity.User;

public record LoginUserDto(

        @NotNull(message = "email should not null")
        @NotBlank(message = "email should not blank")
        String email,

        @NotNull(message = "password should not null")
        @NotBlank(message = "password should not blank")
        String password
){}
