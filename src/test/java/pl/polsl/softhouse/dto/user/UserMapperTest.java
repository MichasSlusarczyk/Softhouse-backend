package pl.polsl.softhouse.dto.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.polsl.softhouse.entities.User;
import pl.polsl.softhouse.entities.enums.UserRole;

@SpringBootTest
public class UserMapperTest {

    //@Autowired
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Test
    void shouldMapUserToDto() {
        // Given
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setFirstName("Meredith");
        user.setLastName("McUser");
        user.setEmail("user@user.com");
        user.setTelNum("123456789");
        user.setRole(UserRole.WORKER);

        // When
        UserDto userDto = userMapper.userToDto(user);

        // Then
        assertNotNull(userDto);
        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getPassword(), userDto.getPassword());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getTelNum(), userDto.getTelNum());
        assertEquals(user.getRole().toString(), userDto.getRole());
        assertEquals(user.isActive(), userDto.isActive());
    }

    @Test
    void shouldMapDtoToUser() {
        // Given
        UserDto userDto = new UserDto();
        userDto.setUsername("testUser");
        userDto.setPassword("testPassword");
        userDto.setFirstName("Meredith");
        userDto.setLastName("McUser");
        userDto.setEmail("user@user.com");
        userDto.setTelNum("123456789");
        userDto.setRole(UserRole.WORKER.toString());

        // When
        User user = userMapper.dtoToUser(userDto);

        // Then
        assertNotNull(user);
        assertEquals(userDto.getUsername(), user.getUsername());
        assertEquals(userDto.getPassword(), user.getPassword());
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getLastName(), user.getLastName());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getTelNum(), user.getTelNum());
        assertEquals(userDto.getRole(), user.getRole().toString());
        assertEquals(userDto.isActive(), user.isActive());
    }

    @Test
    void shouldUpdateUser() {
        // Given
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setFirstName("Meredith");
        user.setLastName("McUser");
        user.setEmail("user@user.com");
        user.setTelNum("123456789");
        user.setRole(UserRole.WORKER);

        UserDto userDto = new UserDto();
        userDto.setUsername("newUsername");
        userDto.setRole(UserRole.ADMIN.toString());

        // When
        User updatedUser = userMapper.updateUser(userDto, user);

        // Then
        assertNotNull(updatedUser);
        assertEquals(userDto.getUsername(), updatedUser.getUsername());
        assertEquals(user.getPassword(), updatedUser.getPassword());
        assertEquals(user.getFirstName(), updatedUser.getFirstName());
        assertEquals(user.getLastName(), updatedUser.getLastName());
        assertEquals(user.getEmail(), updatedUser.getEmail());
        assertEquals(user.getTelNum(), updatedUser.getTelNum());
        assertEquals(userDto.getRole(), updatedUser.getRole().toString());
        assertEquals(user.isActive(), updatedUser.isActive());
    }
}
