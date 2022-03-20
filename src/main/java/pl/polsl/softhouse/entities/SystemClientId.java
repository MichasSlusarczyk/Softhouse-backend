package pl.polsl.softhouse.entities;

import java.io.Serializable;

public class SystemClientId implements Serializable {
    
    private Long system;
    private Long client;
    
    public Long getSystem() {
        return system;
    }
    
    public void setSystem(Long system) {
        this.system = system;
    }
    
    public Long getClient() {
        return client;
    }
    
    public void setClient(Long client) {
        this.client = client;
    }
}
