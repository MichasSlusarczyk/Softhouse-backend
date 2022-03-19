package pl.polsl.softhouse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.polsl.softhouse.entities.User;
import pl.polsl.softhouse.exceptions.user.UserAlreadyExistsException;
import pl.polsl.softhouse.exceptions.user.UserNotFoundException;
import pl.polsl.softhouse.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public User addUser(User user) {
        if (!checkUsernameUnique(user.getUsername())) {
            throw new UserAlreadyExistsException("User already exists."); // TODO
        }
        
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
    public User updateUser(Long id, String firstName, String lastName, String username, String password, Boolean active) {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isEmpty()) {
            throw new UserNotFoundException("User does not exist.");
        }
        User user = foundUser.get();

        if (username != null && !username.isBlank() && !username.equals(user.getUsername())) {
            if (!checkUsernameUnique(username)) {
                throw new UserAlreadyExistsException("User already exists."); // TODO
            }
            user.setUsername(username);
        }

        if (firstName != null && !firstName.isBlank() && !firstName.equals(user.getFirstName())) {
            user.setFirstName(firstName);
        }

        if (lastName != null && !lastName.isBlank() && !lastName.equals(user.getLastName())) {
            user.setLastName(password);
        }

        if (password != null && !password.isBlank() && !password.equals(user.getPassword())) {
            user.setPassword(password);
        }

        if (active != null && active != user.isActive()) {
            user.setActive(active);
        }

        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        Optional<User> foundUser = userRepository.findByUsername(username);
        if (foundUser.isEmpty()) {
            throw new UserNotFoundException("User does not exist.");
        }

        return foundUser.get();
    }

    private boolean checkUsernameUnique(String username) {
        Optional<User> foundUser = userRepository.findByUsername(username);
        return foundUser.isEmpty();
    }
}
