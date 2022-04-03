package pl.polsl.softhouse.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import pl.polsl.softhouse.entities.enums.UserRole;

@Entity
@Table(name="users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class UserEntity {

    private static final String GEN_NAME = "user_sequence";
    public static final int MAX_USERNAME_LENGTH = 64;
    public static final int MAX_NAME_LENGTH = 64;
    public static final int MAX_EMAIL_LENGTH = 64;

    @Id
    @SequenceGenerator(name = GEN_NAME, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GEN_NAME)
    private Long id;

    @Column(nullable = false, length = MAX_USERNAME_LENGTH)
    @NotNull
    @Size(min = 4, max = MAX_USERNAME_LENGTH,
            message = "Username must be between 4 and " + MAX_USERNAME_LENGTH + " characters")
    private String username;

    @Column(nullable = false, length = 64)
    @NotNull
    //@Size(min = 64, max = 64, message = "password hash must be 64 characters long") // Uncomment this when hashing is implemented.
    private String password;

    @Column(nullable = false)
    @NotNull
    private Boolean active = true;

    @Column(nullable = false, length = MAX_NAME_LENGTH)
    @NotNull
    @Size(min = 1, max = MAX_NAME_LENGTH,
            message = "First name must be between 1 and " + MAX_USERNAME_LENGTH + " characters")
    private String firstName;

    @Column(nullable = false, length = MAX_NAME_LENGTH)
    @NotNull
    @Size(min = 1, max = MAX_NAME_LENGTH,
            message = "Last name must be between 1 and " + MAX_USERNAME_LENGTH + " characters")
    private String lastName;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    @NotNull
    private UserRole role;

    @Column(nullable = false, length = MAX_EMAIL_LENGTH)
    @NotNull
    @Email(message = "Must be a valid email")
    private String email;

    @Column(nullable = false, length = 9)
    @NotNull
    @Pattern(regexp = "\\d{9}",
                message = "Must be a valid 9-digit number")
    private String telNum;

    public UserEntity(Long id,
                      String username,
                      String password,
                      Boolean active,
                      String firstName,
                      String lastName,
                      UserRole role,
                      String email,
                      String telNum) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.active = active;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.telNum = telNum;
    }

    public UserEntity() {
    }

    public UserEntity(String username,
                      String password,
                      Boolean active,
                      String firstName,
                      String lastName,
                      UserRole role,
                      String email,
                      String telNum) {
        this.id = 0L;
        this.username = username;
        this.password = password;
        this.active = active;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.telNum = telNum;
    }

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }
}
