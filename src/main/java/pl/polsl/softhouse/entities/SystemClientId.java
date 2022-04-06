package pl.polsl.softhouse.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SystemClientId implements Serializable {

    @Column(name = "system_id")
    private Long systemId;

    @Column(name = "client_id")
    private Long clientId;
    
    public Long getSystemId() {
        return systemId;
    }
    
    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }
    
    public Long getClientId() {
        return clientId;
    }
    
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
