package pl.polsl.softhouse.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "system_client",
        uniqueConstraints = { @UniqueConstraint(columnNames = { "system_id", "client_id", "version" }) })
public class SystemClient {

    @EmbeddedId
    SystemClientId id;

    @ManyToOne
    @MapsId("systemId")
    @JoinColumn(name = "system_id")
    @NotNull
    private SystemEntity system;

    @ManyToOne
    @MapsId("clientId")
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
}
