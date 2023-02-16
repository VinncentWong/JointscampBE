package jointscamp.be.mapper.user;

import jointscamp.be.dto.user.LoginUserDto;
import jointscamp.be.dto.user.RegisterUserDto;
import jointscamp.be.dto.user.UpdateUserDto;
import jointscamp.be.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    public final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "user.username", source = "dto.username")
    @Mapping(target = "user.whatsappContact", source = "dto.whatsappContact")
    @Mapping(target = "user.lineContact", source = "dto.lineContact")
    @Mapping(target = "user.instagramContact", source = "dto.instagramContact")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateUserFromUpdateDto(UpdateUserDto dto, @MappingTarget User user);
}
