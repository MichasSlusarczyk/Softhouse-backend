package pl.polsl.softhouse.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import pl.polsl.softhouse.components.security.JwtUtil;
import pl.polsl.softhouse.dto.user.UserGetDto;
import pl.polsl.softhouse.dto.user.UserMapper;
import pl.polsl.softhouse.dto.user.UserPostDto;
import pl.polsl.softhouse.entities.UserEntity;
import pl.polsl.softhouse.exceptions.AuthenticationException;
import pl.polsl.softhouse.exceptions.InvalidDataException;
import pl.polsl.softhouse.exceptions.user.UserAlreadyExistsException;
import pl.polsl.softhouse.exceptions.user.UserNotFoundException;
import pl.polsl.softhouse.repositories.UserRepository;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final Validator validator;

    private final AuthenticationManager authManager;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       Validator validator,
                       AuthenticationManager authManager) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.validator = validator;
        this.authManager = authManager;
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
        validateOrThrow(user);

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
        validateOrThrow(user);

        userRepository.save(user);
    }

    public String loginAndGetJwt(String username, String password) {
        if (username == null || username.isBlank() ||
                password == null || password.isBlank()) {
            throw new InvalidDataException("No data sent.");
        }

        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            User user = (User) auth.getPrincipal();

            return new JwtUtil().generateToken(user.getUsername());
        } catch (BadCredentialsException e) {
            throw new AuthenticationException(e.getMessage());
        }
    }

    private void validateOrThrow(UserEntity user) {
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private boolean checkIfUsernameUnique(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }
}
