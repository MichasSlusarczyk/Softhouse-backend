package pl.polsl.softhouse.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Issue extends AbstractWorkUnit {
    
    private static final int MAX_DESC_LENGTH = 512;
    private static final String GEN_NAME = "issue_sequence";

    @Id
    @SequenceGenerator(name=GEN_NAME, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator=GEN_NAME)
    private Long id;

    @Column(nullable = false, length = MAX_DESC_LENGTH)
    private String description;

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id", nullable = true)
    private User productManager;

    @ManyToOne(optional = false)
    @JoinColumn(name = "request_id", nullable = false)
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

    public User getProductManager() {
        return productManager;
    }

    public void setProductManager(User productManager) {
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
}
