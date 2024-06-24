package com.paintingscollectors.repository;

import com.paintingscollectors.model.entity.PaintingEntity;
import com.paintingscollectors.model.entity.StyleEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Set;

@Repository
public interface PaintingRepository extends JpaRepository<PaintingEntity, Long> {
    Set<PaintingEntity> findAllByStyleEquals(StyleEntity language);
    Set<PaintingEntity> findAllByOwnerUsernameNot(String username);
    @Query("SELECT p FROM PaintingEntity p ORDER BY p.votes DESC, p.name ASC")
    List<PaintingEntity> findTop2MostVotedPaintings(Pageable pageable);

}
