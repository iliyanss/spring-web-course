package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.entity.StyleEntity;
import com.example.spotifyplaylistapp.model.entity.StyleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.Style;
import java.util.Optional;

@Repository
public interface StyleRepository extends JpaRepository<StyleEntity, Long> {
    Optional<StyleEntity> findByStyleName(StyleEnum styleEnum);
}
