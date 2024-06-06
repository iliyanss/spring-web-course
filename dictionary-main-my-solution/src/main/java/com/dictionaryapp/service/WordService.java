package com.dictionaryapp.service;

import com.dictionaryapp.model.entity.LanguageEntity;
import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.model.entity.UserEntity;
import com.dictionaryapp.model.entity.WordEntity;
import com.dictionaryapp.model.entity.dtos.AddwordDTO;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {

    private final WordRepository wordRepository;
    private final LanguageService languageService;
    private final UserService userService;
    private final UserRepository userRepository;

    public WordService(WordRepository wordRepository, LanguageService languageService, UserService userService, UserRepository userRepository) {
        this.wordRepository = wordRepository;
        this.languageService = languageService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public void addWord(AddwordDTO addwordDTO, Long id) {
        WordEntity wordEntity = new WordEntity();
        LanguageEntity languageEntity = this.languageService.findLanguage(addwordDTO.getLanguage());
        UserEntity userEntity = this.userService.findUserById(id).orElse(null);
        wordEntity.setTranslation(addwordDTO.getTranslation());
        wordEntity.setLanguage(languageEntity);
        wordEntity.setTerm(addwordDTO.getTerm());
        wordEntity.setExample(addwordDTO.getExample());
        wordEntity.setAddedBy(userEntity);
        wordEntity.setInputDate(addwordDTO.getInputDate());
        userEntity.getAddedWords().add(wordEntity);
        wordRepository.save(wordEntity);
        userRepository.save(userEntity);

    }
    public List<WordEntity>getAllGermanWords(){
        LanguageEntity language = this.languageService.findLanguage(LanguageEnum.GERMAN);
        return this.wordRepository.findAllByLanguageEquals(language);
    }
    public List<WordEntity>getAllFrenchWords(){
        LanguageEntity language = this.languageService.findLanguage(LanguageEnum.FRENCH);
        return this.wordRepository.findAllByLanguageEquals(language);
    }
    public List<WordEntity>getAllSpanishWords(){
        LanguageEntity language = this.languageService.findLanguage(LanguageEnum.SPANISH);
        return this.wordRepository.findAllByLanguageEquals(language);
    }
    public List<WordEntity>getAllItalianWords(){
        LanguageEntity language = this.languageService.findLanguage(LanguageEnum.ITALIAN);
        return this.wordRepository.findAllByLanguageEquals(language);
    }

    public boolean removeWordById(Long id) {
        WordEntity wordById = this.wordRepository.findById(id).orElse(null);
        if (wordById != null) {
            wordById.getAddedBy().getAddedWords().remove(wordById);
            userRepository.save(wordById.getAddedBy());
            wordById.setAddedBy(null);
            this.wordRepository.delete(wordById);
            return true;
        }
        return false;
    }

    public boolean removeAllWords() {
        List<WordEntity> allWords = this.wordRepository.findAll();
        for (WordEntity wordEntity : allWords) {
            this.removeWordById(wordEntity.getId());
        }
        if (allWords.size() > 0) {
            return false;
        }
        return true;
    }
}
