package pl.polsl.softhouse.components;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.polsl.softhouse.entities.UserEntity;
import pl.polsl.softhouse.entities.enums.UserRole;
import pl.polsl.softhouse.repositories.UserRepository;

@Component
public class UserDataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;

    public UserDataSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedUsers();
    }

    private void seedUsers() {
        UserEntity user = new UserEntity(0L,
                "admin",
                "admin",
                true,
                "John",
                "Admin",
                UserRole.ADMIN,
                "user@seeded.com",
                "123456789");

        userRepository.save(user);
    }
}
