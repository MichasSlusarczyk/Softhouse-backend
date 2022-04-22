package pl.polsl.softhouse.components;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.polsl.softhouse.entities.UserEntity;
import pl.polsl.softhouse.entities.enums.UserRole;
import pl.polsl.softhouse.repositories.UserRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class UserDataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;

    public UserDataSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        seedUsers();
    }

    private void seedUsers() {
        List<UserEntity> users = Arrays.asList(
                new UserEntity(null,
                        "admin",
                        "admin",
                        true,
                        "John",
                        "Admin",
                        UserRole.ADMIN,
                        "user@seeded.com",
                        "123456789"),
                new UserEntity(null,
                        "accMan",
                        "asdf",
                        true,
                        "Larwa",
                        "Kroft",
                        UserRole.ACCOUNT_MANAGER,
                        "user@seeded.com",
                        "123456789"),
                new UserEntity(null,
                        "prodMan",
                        "nostromo",
                        true,
                        "Ellen",
                        "Ripley",
                        UserRole.PRODUCT_MANAGER,
                        "user@seeded.com",
                        "123456789"),
                new UserEntity(null,
                        "worker",
                        "zwiazekzawodowy",
                        true,
                        "Adrian",
                        "Zandberg",
                        UserRole.WORKER,
                        "user@seeded.com",
                        "123456789")
        );

        userRepository.saveAll(users);
    }
}
