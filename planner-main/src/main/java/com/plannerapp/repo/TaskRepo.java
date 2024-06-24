package com.plannerapp.repo;

import com.plannerapp.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

    Set<Task> findAllByAssignedToFalse();
    Set<Task> findAllByAssignedToIsNull();
    Set<Task> findAllByAssignedToIsFalse();
}
