package jointscamp.be.dto.user;

import org.hibernate.validator.constraints.Length;

public record UpdateUserDto(

        @Length(min = 6, message = "username length should be >= 6")
        String username,

        @Length(min = 6, message = "whatsapp contact length should be >= 6")
        String whatsappContact,

        @Length(min = 4, message = "line contact length should be >= 4")
        String lineContact,

        @Length(min = 4, message = "instagram contact length should be >= 4")
        String instagramContact
) {}
