package com.paintingscollectors.repository;

import com.paintingscollectors.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity>findByEmail(String email);
    Optional<UserEntity>findByUsername(String username);

    Set<UserEntity> findAllBy();
}
