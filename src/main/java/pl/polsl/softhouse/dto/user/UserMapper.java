package pl.polsl.softhouse.dto.user;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import pl.polsl.softhouse.entities.UserEntity;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserGetDto userToGetDto(UserEntity user);

    @Mapping(target = "id", expression = "java(null)")
    @Mapping(target = "active", constant = "true")
    UserEntity createUserFromDto(UserPostDto userDto);

    @Mapping(target = "id", ignore = true)
    UserEntity updateUser(UserPostDto userDto, @MappingTarget UserEntity user);
}
