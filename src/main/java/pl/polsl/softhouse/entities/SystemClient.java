package pl.polsl.softhouse.entities;

import javax.persistence.*;

@Entity
@Table(name = "system_client")
@IdClass(SystemClientId.class)
public class SystemClient {

    @Id
    @ManyToOne
    @JoinColumn(name = "system_id")
    private SystemEntity system;

    @Id
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(nullable = false, length = 8)
    private String version;

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
