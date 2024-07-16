package bg.softuni.mobilele.models.entities;

import bg.softuni.mobilele.models.enums.UserRoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Table(name = "roles")
@Entity
public class UserRoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private UserRoleEnum role;

    public UserRoleEntity() {

    }

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
