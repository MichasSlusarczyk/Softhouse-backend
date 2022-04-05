package pl.polsl.softhouse.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "system_client",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"system_id", "client_id", "version"})})
public class SystemClient {

    private static final String GEN_NAME = "system_client_sequence";

    @Id
    @SequenceGenerator(name = GEN_NAME, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GEN_NAME)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "system_id")
    @NotNull
    private SystemEntity system;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @NotNull
    private Client client;

    @Column(nullable = false, length = 8)
    @NotNull
    private String version = "";

    public SystemEntity getSystem() {
        return system;
    }

    public void setSystem(SystemEntity system) {
        this.system = system;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
