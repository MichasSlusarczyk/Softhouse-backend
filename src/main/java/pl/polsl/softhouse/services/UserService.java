package pl.polsl.softhouse.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import pl.polsl.softhouse.dto.user.UserAuthDto;
import pl.polsl.softhouse.dto.user.UserDto;
import pl.polsl.softhouse.dto.user.UserInfoDto;
import pl.polsl.softhouse.dto.user.UserMapper;
import pl.polsl.softhouse.entities.UserEntity;
import pl.polsl.softhouse.exceptions.InvalidDataException;
import pl.polsl.softhouse.exceptions.user.UserAlreadyExistsException;
import pl.polsl.softhouse.exceptions.user.UserNotFoundException;
import pl.polsl.softhouse.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserInfoDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::userToInfoDto)
                .collect(Collectors.toList());
    }

    public UserInfoDto getUserById(Long id) {
        if (id == null) {
            throw new InvalidDataException("No data sent."); // TODO.
        }

        return userRepository.findById(id)
                .map(userMapper::userToInfoDto)
                .orElseThrow(() -> new UserNotFoundException(id)); // TODO
    }

    public void addUser(UserDto userDto) {
        if (userDto == null) {
            throw new InvalidDataException("No data sent."); // TODO: Custom exception maybe.
        }

        if (!checkIfUsernameUnique(userDto.getUsername())) {
            throw UserAlreadyExistsException.fromUsername(userDto.getUsername()); // TODO
        }

        UserEntity user = userMapper.createUserFromDto(userDto);

        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (id == null) {
            throw new InvalidDataException("No data sent."); // TODO: Custom exception maybe.
        }

        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id); // TODO
        }

        userRepository.deleteById(id);
    }

    // TODO: Test.
    public void updateUser(Long id, UserDto userDto) {
        if (id == null || userDto == null) {
            throw new InvalidDataException("No data sent."); // TODO: Custom exception maybe.
        }

        userRepository.findById(id)
                .map(foundUser -> userMapper.updateUser(userDto, foundUser))
                .map(userRepository::save)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public UserAuthDto getUserAuthByUsername(String username) {
        if (username == null) {
            throw new InvalidDataException("No data sent."); // TODO: Custom exception maybe.
        }

        return userRepository.findByUsername(username)
                .map(userMapper::userToAuthDto)
                .orElseThrow(() -> UserNotFoundException.fromUsername(username));
    }

    private boolean checkIfUsernameUnique(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }
}
