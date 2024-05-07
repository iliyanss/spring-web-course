package bg.softuni.MobiLeLeLe.models.entities;

import bg.softuni.MobiLeLeLe.models.enums.UserRoleEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole {
    private Long id;
    private UserRoleEnum userRole;

    public UserRole() {

    }
    @Column(nullable = false, name = "user_role")
    @Enumerated(EnumType.STRING)
    public UserRoleEnum getUserRole() {
        return userRole;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public UserRole setId(Long id) {
        this.id = id;
        return this;
    }

    public UserRole setUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
        return this;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", userRole=" + userRole +
                '}';
    }
}
