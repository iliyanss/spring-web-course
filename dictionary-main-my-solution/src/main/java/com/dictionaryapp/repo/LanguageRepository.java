package com.dictionaryapp.repo;

import com.dictionaryapp.model.entity.LanguageEntity;
import com.dictionaryapp.model.entity.LanguageEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageEntity, Long> {


    Optional<LanguageEntity> findByLanguageName(LanguageEnum languageEnum);
}
