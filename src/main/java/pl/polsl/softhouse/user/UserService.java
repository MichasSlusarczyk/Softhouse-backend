package pl.polsl.softhouse.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void addUser(User user) {
        if (!checkUniqueUsername(user.getUsername())) {
            throw new IllegalArgumentException("User already exists."); // TODO
        }
        
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User does not exist."); // TODO
        }

        userRepository.deleteById(id);
    }

    public void updateUser(Long id, String username, String password, Boolean active) {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isEmpty()) {
            throw new IllegalArgumentException("User does not exist.");
        }
        User user = foundUser.get();

        if (username != null && !username.isBlank() && !username.equals(user.getUsername())) {
            if (!checkUniqueUsername(username)) {
                throw new IllegalArgumentException("User already exists."); // TODO
            }
            user.setUsername(username);
        }

        if (password != null && !password.isBlank() && !password.equals(user.getPassword())) {
            user.setPassword(password);
        }

        if (active != null && active != user.isActive()) {
            user.setActive(active);
        }

        userRepository.save(user);
    }

    private boolean checkUniqueUsername(String username) {
        Optional<User> foundUser = userRepository.findByUsername(username);
        return foundUser.isEmpty();
    }
}
