package pl.polsl.softhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.softhouse.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
