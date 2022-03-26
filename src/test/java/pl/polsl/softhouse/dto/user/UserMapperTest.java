package pl.polsl.softhouse.dto.user;

import org.junit.jupiter.api.Test;
import pl.polsl.softhouse.entities.UserEntity;
import pl.polsl.softhouse.entities.enums.UserRole;

import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {

    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Test
    void shouldMapUserToInfoDto() {
        // Given
        UserEntity user = new UserEntity();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setFirstName("Meredith");
        user.setLastName("McUser");
        user.setEmail("user@user.com");
        user.setTelNum("123456789");
        user.setActive(true);
        user.setRole(UserRole.WORKER);

        // When
        UserInfoDto userInfoDto = userMapper.userToInfoDto(user);

        // Then
        assertNotNull(userInfoDto);
        assertEquals(user.getUsername(), userInfoDto.getUsername());
        assertEquals(user.getFirstName(), userInfoDto.getFirstName());
        assertEquals(user.getLastName(), userInfoDto.getLastName());
        assertEquals(user.getEmail(), userInfoDto.getEmail());
        assertEquals(user.getTelNum(), userInfoDto.getTelNum());
        assertEquals(user.getRole().toString(), userInfoDto.getRole());
        assertEquals(user.isActive(), userInfoDto.getActive());
    }

    @Test
    void shouldCreateUserFromDto() {
        // Given
        UserDto userDto = new UserDto();
        userDto.setUsername("testUser");
        userDto.setPassword("testPassword");
        userDto.setFirstName("Meredith");
        userDto.setLastName("McUser");
        userDto.setEmail("user@user.com");
        userDto.setTelNum("123456789");
        userDto.setRole(UserRole.WORKER.toString());
        userDto.setActive(true);

        // When
        UserEntity user = userMapper.createUserFromDto(userDto);

        // Then
        assertNotNull(user);
        assertEquals(0L, user.getId());
        assertEquals(userDto.getUsername(), user.getUsername());
        assertEquals(userDto.getPassword(), user.getPassword());
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getLastName(), user.getLastName());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getTelNum(), user.getTelNum());
        assertEquals(userDto.getRole(), user.getRole().toString());
        assertEquals(true, user.isActive());
    }

    @Test
    void shouldCreateUserFromDtoWithDefaultValues() {
        // Given
        UserDto userDto = new UserDto();
        userDto.setId(100L);
        userDto.setUsername("testUser");
        userDto.setPassword("testPassword");
        userDto.setFirstName("Meredith");
        userDto.setLastName("McUser");
        userDto.setEmail("user@user.com");
        userDto.setTelNum("123456789");
        userDto.setRole(UserRole.WORKER.toString());
        userDto.setActive(false);

        // When
        UserEntity user = userMapper.createUserFromDto(userDto);

        // Then
        assertNotNull(user);
        assertEquals(0L, user.getId());
        assertEquals(userDto.getUsername(), user.getUsername());
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getLastName(), user.getLastName());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getTelNum(), user.getTelNum());
        assertEquals(userDto.getRole(), user.getRole().toString());
        assertEquals(true, user.isActive());
    }

    @Test
    void shouldUpdateUser() {
        // Given
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setFirstName("Meredith");
        user.setLastName("McUser");
        user.setEmail("user@user.com");
        user.setTelNum("123456789");
        user.setRole(UserRole.WORKER);

        UserDto userDto = new UserDto();
        userDto.setId(20L);
        userDto.setUsername("newUsername");
        userDto.setRole(UserRole.ADMIN.toString());

        // When
        UserEntity updatedUser = userMapper.updateUser(userDto, user);

        // Then
        assertNotNull(updatedUser);
        assertEquals(1L, updatedUser.getId());
        assertEquals(userDto.getUsername(), updatedUser.getUsername());
        assertEquals(user.getPassword(), updatedUser.getPassword());
        assertEquals(user.getFirstName(), updatedUser.getFirstName());
        assertEquals(user.getLastName(), updatedUser.getLastName());
        assertEquals(user.getEmail(), updatedUser.getEmail());
        assertEquals(user.getTelNum(), updatedUser.getTelNum());
        assertEquals(userDto.getRole(), updatedUser.getRole().toString());
        assertEquals(user.isActive(), updatedUser.isActive());
    }

    @Test
    void shouldMapUserToAuthDto() {
        // Given
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setFirstName("Meredith");
        user.setLastName("McUser");
        user.setEmail("user@user.com");
        user.setTelNum("123456789");
        user.setRole(UserRole.WORKER);

        // When
        UserAuthDto userDto = userMapper.userToAuthDto(user);

        // Then
        assertNotNull(userDto);
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getPassword(), userDto.getPassword());
        assertEquals(user.isActive(), userDto.getActive());
    }
}
