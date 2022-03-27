package pl.polsl.softhouse.dto.user;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import pl.polsl.softhouse.entities.UserEntity;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserInfoDto userToInfoDto(UserEntity user);

    @Mapping(target = "id", constant = "0L")
    @Mapping(target = "active", constant = "true")
    UserEntity createUserFromDto(UserDto userDto);

    @Mapping(target = "id", ignore = true)
    UserEntity updateUser(UserDto userDto, @MappingTarget UserEntity user);

    UserAuthDto userToAuthDto(UserEntity user);
}
