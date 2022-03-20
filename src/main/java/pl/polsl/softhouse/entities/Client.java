package pl.polsl.softhouse.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Client {
    
    private static final int MAX_NAME_LENGTH = 256;
    private static final String GEN_NAME = "client_sequence";

    @Id
    @SequenceGenerator(name=GEN_NAME, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator=GEN_NAME)
    private Long id;

    @Column(nullable = false, length = MAX_NAME_LENGTH)
    private String name;

    @OneToMany(mappedBy = "client")
    private List<SystemClient> systemAssoc;

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

    public List<SystemClient> getSystemAssoc() {
        return systemAssoc;
    }

    public void setSystemAssoc(List<SystemClient> systemAssoc) {
        this.systemAssoc = systemAssoc;
    }
}
