package pl.polsl.softhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.polsl.softhouse.entities.Issue;
import pl.polsl.softhouse.entities.Request;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query("SELECT r FROM Request r join r.accountManager accountManager WHERE accountManager.id = ?1")
    Optional<List<Request>> findAllByUserId(Long userId);
}
