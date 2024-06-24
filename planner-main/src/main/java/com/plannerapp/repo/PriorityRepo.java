package com.plannerapp.repo;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.PriorityEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriorityRepo extends JpaRepository<Priority, Long> {

    Optional<Priority> findByPriorityName(PriorityEnum priorityEnum);
}
