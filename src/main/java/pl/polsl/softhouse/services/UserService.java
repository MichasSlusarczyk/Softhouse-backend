package pl.polsl.softhouse.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import pl.polsl.softhouse.components.GenericValidator;
import pl.polsl.softhouse.dto.user.UserGetDto;
import pl.polsl.softhouse.dto.user.UserMapper;
import pl.polsl.softhouse.dto.user.UserPostDto;
import pl.polsl.softhouse.entities.UserEntity;
import pl.polsl.softhouse.exceptions.AuthenticationException;
import pl.polsl.softhouse.exceptions.InvalidDataException;
import pl.polsl.softhouse.exceptions.user.UserAlreadyExistsException;
import pl.polsl.softhouse.exceptions.user.UserNotFoundException;
import pl.polsl.softhouse.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final GenericValidator<UserEntity> validator;

    public UserService(UserRepository userRepository, UserMapper userMapper, GenericValidator<UserEntity> validator) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.validator = validator;
    }

    public List<UserGetDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::userToGetDto)
                .collect(Collectors.toList());
    }

    public UserGetDto getUserById(Long id) {
        if (id == null) {
            throw new InvalidDataException("No ID provided.");
        }

        return userRepository.findById(id)
                .map(userMapper::userToGetDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public long addUser(UserPostDto userDto) {
        if (userDto == null) {
            throw new InvalidDataException("No data sent.");
        }

        if (!checkIfUsernameUnique(userDto.getUsername())) {
            throw UserAlreadyExistsException.fromUsername(userDto.getUsername());
        }

        UserEntity user = userMapper.createUserFromDto(userDto);
        validator.validateOrThrow(user);

        return userRepository.save(user).getId();
    }

    public void deleteUser(Long id) {
        if (id == null) {
            throw new InvalidDataException("No ID provided.");
        }

        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        userRepository.deleteById(id);
    }

    public void updateUser(Long id, UserPostDto userDto) {
        if (id == null || userDto == null) {
            throw new InvalidDataException("No data sent.");
        }

        UserEntity user = userRepository.findById(id)
                .map(foundUser -> userMapper.updateUser(userDto, foundUser))
                .orElseThrow(() -> new UserNotFoundException(id));
        validator.validateOrThrow(user);

        userRepository.save(user);
    }

    public UserGetDto authorizeUser(String username, String password) {
        if (username == null || password == null) {
            throw new InvalidDataException("No data sent.");
        }

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> UserNotFoundException.fromUsername(username));

        if (user.getPassword().equals(password)) {
            return userMapper.userToGetDto(user);
        } else {
            throw new AuthenticationException("Invalid password.");
        }
    }

    private boolean checkIfUsernameUnique(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }
}
