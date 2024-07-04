package bg.softuni.buildershop.repository;

import bg.softuni.buildershop.model.entity.UserRoleEntity;
import bg.softuni.buildershop.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    List<UserRoleEntity> findAllByRoleIn(List<UserRoleEnum> roles);
    UserRoleEntity findByRole(UserRoleEnum role);

}
