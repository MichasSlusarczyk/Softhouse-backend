package pl.polsl.softhouse.dto.user;

import org.junit.jupiter.api.Test;
import pl.polsl.softhouse.entities.UserEntity;
import pl.polsl.softhouse.entities.enums.UserRole;

import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {

    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Test
    void shouldMapUserToGetDto() {
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
        UserGetDto userDto = userMapper.userToGetDto(user);

        // Then
        assertNotNull(userDto);
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getTelNum(), userDto.getTelNum());
        assertEquals(user.getRole(), userDto.getRole());
        assertEquals(user.getActive(), userDto.getActive());
    }

    @Test
    void shouldCreateUserFromPostDto() {
        // Given
        UserPostDto userDto = new UserPostDto();
        userDto.setUsername("testUser");
        userDto.setPassword("testPassword");
        userDto.setFirstName("Meredith");
        userDto.setLastName("McUser");
        userDto.setEmail("user@user.com");
        userDto.setTelNum("123456789");
        userDto.setRole(UserRole.WORKER);

        // When
        UserEntity user = userMapper.createUserFromDto(userDto);

        // Then
        assertNotNull(user);
        assertNull(user.getId());
        assertEquals(userDto.getUsername(), user.getUsername());
        assertEquals(userDto.getPassword(), user.getPassword());
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getLastName(), user.getLastName());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getTelNum(), user.getTelNum());
        assertEquals(userDto.getRole(), user.getRole());
        assertEquals(true, user.getActive());
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

        UserPostDto userDto = new UserPostDto();
        userDto.setUsername("newUsername");
        userDto.setRole(UserRole.ADMIN);

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
        assertEquals(userDto.getRole(), updatedUser.getRole());
        assertEquals(user.getActive(), updatedUser.getActive());
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
        assertEquals(user.getActive(), userDto.getActive());
    }
}
