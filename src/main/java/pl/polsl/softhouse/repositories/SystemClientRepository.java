package pl.polsl.softhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.softhouse.entities.SystemClient;

public interface SystemClientRepository extends JpaRepository<SystemClient, Long> {
}
