package pl.polsl.softhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.polsl.softhouse.entities.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
    
}
