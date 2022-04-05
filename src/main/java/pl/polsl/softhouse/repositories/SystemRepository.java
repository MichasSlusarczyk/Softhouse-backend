package pl.polsl.softhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.softhouse.entities.SystemEntity;

public interface SystemRepository extends JpaRepository<SystemEntity, Long> {
}
