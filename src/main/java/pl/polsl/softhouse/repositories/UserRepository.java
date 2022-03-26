package pl.polsl.softhouse.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.polsl.softhouse.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.username = ?1")
    Optional<UserEntity> findByUsername(String username);
}
