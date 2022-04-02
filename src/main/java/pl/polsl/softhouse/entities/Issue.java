package pl.polsl.softhouse.entities;

import pl.polsl.softhouse.entities.enums.IssueType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "issues")
public class Issue extends AbstractWorkUnit {
    
    public static final int MAX_DESC_LENGTH = 512;
    private static final String GEN_NAME = "issue_sequence";

    @Id
    @SequenceGenerator(name=GEN_NAME, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator=GEN_NAME)
    private Long id;

    @Column(nullable = false, length = MAX_DESC_LENGTH)
    @NotNull
    @Size(max = MAX_DESC_LENGTH)
    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private IssueType type;

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id", nullable = true)
    private UserEntity productManager;

    @ManyToOne(optional = false)
    @JoinColumn(name = "request_id", nullable = false)
    @NotNull
    private Request request;

    @OneToMany(mappedBy = "issue")
    private List<Task> tasks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getProductManager() {
        return productManager;
    }

    public void setProductManager(UserEntity productManager) {
        this.productManager = productManager;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public IssueType getType() {
        return type;
    }

    public void setType(IssueType type) { this.type = type; }
}
