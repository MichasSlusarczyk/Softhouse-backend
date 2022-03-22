package pl.polsl.softhouse.dto.user;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import pl.polsl.softhouse.entities.User;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToDto(User user);
    User dtoToUser(UserDto userDto);
    User updateUser(UserDto userDto, @MappingTarget User user);

}
