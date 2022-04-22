package pl.polsl.softhouse.entities;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "systems")
public class SystemEntity {

    public static final int MAX_NAME_LENGTH = 256;
    private static final String GEN_NAME = "system_sequence";

    @Id
    @SequenceGenerator(name = GEN_NAME, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GEN_NAME)
    private Long id;

    @Column(nullable = false, length = MAX_NAME_LENGTH, unique = true)
    @NotNull
    @Size(max = MAX_NAME_LENGTH)
    private String name = "";

    @OneToMany(mappedBy = "system")
    private List<SystemClient> clientAssoc;

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

    public List<SystemClient> getClientAssoc() {
        return clientAssoc;
    }

    public void setClientAssoc(List<SystemClient> clientAssoc) {
        this.clientAssoc = clientAssoc;
    }
}
