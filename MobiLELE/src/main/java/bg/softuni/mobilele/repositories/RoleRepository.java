package bg.softuni.mobilele.repositories;

import bg.softuni.mobilele.models.entities.UserRoleEntity;
import bg.softuni.mobilele.models.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<UserRoleEntity, Long> {

    UserRoleEntity findByRole(UserRoleEnum userRoleEnum);
}
