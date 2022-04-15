package pl.polsl.softhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.polsl.softhouse.entities.Issue;
import pl.polsl.softhouse.entities.enums.WorkStatus;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    @Query("SELECT i FROM Issue i join i.productManager productManager WHERE productManager.id = ?1")
    List<Issue> findAllIssuesByUserId(Long userId);

    @Query("SELECT i FROM Issue i WHERE i.status = ?1")
    List<Issue> findAllIssuesByStatus(WorkStatus status);

    //TODO: Check if it's possible to map those two queries together later instead of creating a separate one
    @Query("SELECT i FROM Issue i join i.productManager productManager WHERE productManager.id = ?1 AND i.status = ?2")
    List<Issue> findAllIssuesByUserIdAndStatus(Long userId, WorkStatus status);
}
