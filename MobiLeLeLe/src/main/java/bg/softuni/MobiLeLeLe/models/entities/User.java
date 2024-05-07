package bg.softuni.MobiLeLeLe.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    boolean isActive;
    private String imageUrl;
    private List<UserRole> roles = new ArrayList<>();

    public User() {

    }
    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }
    @Column(name = "is_active")
    public boolean isActive() {
        return isActive;
    }
    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }
    @ManyToMany(fetch = FetchType.EAGER)
    public List<UserRole> getRoles() {
        return roles;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User setActive(boolean active) {
        isActive = active;
        return this;
    }

    public User setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public User setRoles(List<UserRole> roles) {
        this.roles = roles;
        return this;
    }

    public User addRole(UserRole role){
        this.roles.add(role);
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isActive=" + isActive +
                ", imageUrl='" + imageUrl + '\'' +
                ", roles=" + roles +
                '}';
    }
}
