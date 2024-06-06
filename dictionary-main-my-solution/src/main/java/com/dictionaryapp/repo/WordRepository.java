package com.dictionaryapp.repo;

import com.dictionaryapp.model.entity.LanguageEntity;
import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.model.entity.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<WordEntity, Long> {

    List<WordEntity>findAllByLanguageEquals(LanguageEntity language);
}
