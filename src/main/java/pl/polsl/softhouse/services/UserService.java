package pl.polsl.softhouse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import pl.polsl.softhouse.dto.user.UserDto;
import pl.polsl.softhouse.dto.user.UserMapper;
import pl.polsl.softhouse.entities.User;
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

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public User addUser(UserDto userDto) {
        if (!checkIfUsernameUnique(userDto.getUsername())) {
            throw new UserAlreadyExistsException("User already exists."); // TODO
        }

        User user = userMapper.dtoToUser(userDto);
        user.setId(0L);
        user.setActive(true);

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User does not exist."); // TODO
        }

        userRepository.deleteById(id);
    }

    // TODO: A lot of parameters, probably not good.
    public User updateUser(Long id, UserDto userDto) {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isEmpty()) {
            throw new UserNotFoundException("User does not exist.");
        }

        User user = userMapper.updateUser(userDto, foundUser.get());

        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        Optional<User> foundUser = userRepository.findByUsername(username);
        if (foundUser.isEmpty()) {
            throw new UserNotFoundException("User does not exist.");
        }

        return foundUser.get();
    }

    private boolean checkIfUsernameUnique(String username) {
        Optional<User> foundUser = userRepository.findByUsername(username);
        return foundUser.isEmpty();
    }
}
