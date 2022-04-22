package pl.polsl.softhouse.components.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.polsl.softhouse.entities.UserEntity;
import pl.polsl.softhouse.exceptions.user.UserNotFoundException;
import pl.polsl.softhouse.repositories.UserRepository;

import java.util.ArrayList;

@Component
public class UserEntityDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserEntityDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> UserNotFoundException.fromUsername(username));
        boolean active = user.getActive();

        return new User(user.getUsername(),
                user.getPassword(),
                active,
                active,
                active,
                active,
                new ArrayList<>());
    }
}
