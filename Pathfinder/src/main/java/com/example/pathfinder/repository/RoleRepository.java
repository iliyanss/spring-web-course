package com.example.pathfinder.repository;

import com.example.pathfinder.model.entities.Role;
import com.example.pathfinder.model.entities.enums.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(UserRoles userRole);
}
