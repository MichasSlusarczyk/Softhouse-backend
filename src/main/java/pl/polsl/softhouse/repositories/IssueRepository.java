package pl.polsl.softhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.polsl.softhouse.entities.Issue;

import java.util.List;
import java.util.Optional;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    @Query("SELECT i FROM Issue i join i.productManager productManager WHERE productManager.id = ?1")
    Optional<List<Issue>> findAllByUserId(Long userId);
}
