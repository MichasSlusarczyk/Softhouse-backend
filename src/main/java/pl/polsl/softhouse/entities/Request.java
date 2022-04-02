package pl.polsl.softhouse.entities;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "requests")
public class Request extends AbstractWorkUnit {
    
    public static final int MAX_DESC_LENGTH = 1024;
    private static final String GEN_NAME = "request_sequence";
    
    @Id
    @SequenceGenerator(name=GEN_NAME, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator=GEN_NAME)
    private Long id;

    @Column(nullable = false, length = MAX_DESC_LENGTH)
    @NotNull
    @Size(max = MAX_DESC_LENGTH)
    private String description = "";

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private UserEntity accountManager;

    @OneToMany(mappedBy = "request")
    private List<Issue> issues;

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

    public UserEntity getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(UserEntity accountManager) {
        this.accountManager = accountManager;
    }
}
