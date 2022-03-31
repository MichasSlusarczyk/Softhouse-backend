package pl.polsl.softhouse.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tasks")
public class Task extends AbstractWorkUnit {
    
    public static final int MAX_NAME_LENGTH = 128;
    private static final String GEN_NAME = "task_sequence";

    @Id
    @SequenceGenerator(name=GEN_NAME, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator=GEN_NAME)
    @NotNull
    private Long id;

    @Column(nullable = false, length = MAX_NAME_LENGTH)
    @NotNull
    @Size(min = 3, max = MAX_NAME_LENGTH)
    private String name = "";

    @ManyToOne(optional = false)
    @JoinColumn(name = "issue_id", nullable = false)
    @NotNull
    private Issue issue;

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id", nullable = true)
    private UserEntity worker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public UserEntity getWorker() {
        return worker;
    }

    public void setWorker(UserEntity worker) {
        this.worker = worker;
    }
}
