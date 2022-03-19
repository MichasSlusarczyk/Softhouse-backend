package pl.polsl.softhouse.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
    name = "users",
    uniqueConstraints = { @UniqueConstraint(columnNames = {"username"}) }
)
public class User {
    
    private static final String GEN_NAME = "user_sequence";
    public static final int MAX_USERNAME_LENGTH = 64;
    public static final int MAX_NAME_LENGTH = 64;

    @Id
    @SequenceGenerator(name=GEN_NAME, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator=GEN_NAME)
    private Long id;

    @Column(nullable = false, length = MAX_USERNAME_LENGTH)
    private String username;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false, length = MAX_NAME_LENGTH)
    private String firstName;

    @Column(nullable = false, length = MAX_NAME_LENGTH)
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
