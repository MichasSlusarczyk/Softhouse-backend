package pl.polsl.softhouse.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(SystemClientId.class)
public class SystemClient {

    @Id
    @ManyToOne
    @JoinColumn(name = "system_id")
    private System system;

    @Id
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(nullable = false, length = 8)
    private String version;

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
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
