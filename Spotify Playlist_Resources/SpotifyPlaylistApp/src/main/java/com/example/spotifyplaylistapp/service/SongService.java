package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.AddsongDTO;
import com.example.spotifyplaylistapp.model.entity.SongEntity;
import com.example.spotifyplaylistapp.model.entity.StyleEntity;
import com.example.spotifyplaylistapp.model.entity.StyleEnum;
import com.example.spotifyplaylistapp.model.entity.UserEntity;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class SongService {
    private final StyleService styleService;
    private final SongRepository songRepository;
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;

    public SongService(StyleService styleService, SongRepository songRepository, LoggedUser loggedUser, UserRepository userRepository) {
        this.styleService = styleService;
        this.songRepository = songRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    public void addSong(AddsongDTO addsongDTO, Long id) {

        StyleEntity styleEntity = this.styleService.findStyle(addsongDTO.getStyle());
        SongEntity songEntity = new SongEntity();

        songEntity.setStyle(styleEntity);
        songEntity.setDuration(addsongDTO.getDuration());
        songEntity.setTitle(addsongDTO.getTitle());
        songEntity.setPerformer(addsongDTO.getPerformer());
        songEntity.setReleaseDate(addsongDTO.getReleaseDate());
        this.songRepository.save(songEntity);
    }

    public List<SongEntity> getAllPopSongs() {
        StyleEntity styleEntity = this.styleService.findStyle(StyleEnum.POP);
        return this.songRepository.findAllByStyleEquals(styleEntity);
    }

    public boolean addSongToPlaylist(Long id) {
        SongEntity songEntity = this.songRepository.findById(id).orElse(null);
        if (songEntity != null) {
            UserEntity current = this.userRepository.findByUsername(loggedUser.getUsername()).orElse(null);
            if (current.getPlaylist().contains(songEntity)) {
                return false;
            }
            current.getPlaylist().add(songEntity);
            userRepository.save(current);
            return true;
        }
        return false;
    }

    public List<SongEntity> getAllRockSongs() {
        StyleEntity styleEntity = this.styleService.findStyle(StyleEnum.ROCK);
        return this.songRepository.findAllByStyleEquals(styleEntity);
    }
    public List<SongEntity> getAllJazzSongs() {
        StyleEntity styleEntity = this.styleService.findStyle(StyleEnum.JAZZ);
        return this.songRepository.findAllByStyleEquals(styleEntity);
    }

    public boolean removeAllSongs() {
        UserEntity userEntity = this.userRepository.findByUsername(loggedUser.getUsername()).orElse(null);
        if (userEntity != null) {
            Iterator<SongEntity> iterator = userEntity.getPlaylist().iterator();
            while (iterator.hasNext()) {
                iterator.next();
                iterator.remove();
            }
            userRepository.save(userEntity);
            return true;
        }

        return false;
    }
}
