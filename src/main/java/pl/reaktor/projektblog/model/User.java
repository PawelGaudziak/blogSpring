package pl.reaktor.projektblog.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "{user.name.notempty}")
    private String name;

    @NotEmpty(message = "{user.lastname.notempty}")
    @Column(name="last_name")
    private String lastName;

    @NotEmpty(message = "{user.email.notempty}") //odwołanie
    @Column(unique = true)
    private String email;

    @Length(min=5, message = "{user.password.min}")
    private String password;

    private boolean active;

    @ManyToOne //relacja
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
    }

    public User(String name, String lastName, String email, String password, boolean active, Role role) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.active = active;
        this.role = role;
    }

    public User(Long id, String name, String lastName, String email, String password, boolean active, Role role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.active = active;
        this.role = role;
    }

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", role=" + role +
                '}';
    }
}
