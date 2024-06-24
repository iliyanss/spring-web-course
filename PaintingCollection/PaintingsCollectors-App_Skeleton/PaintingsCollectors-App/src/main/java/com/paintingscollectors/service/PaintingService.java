package com.paintingscollectors.service;

import com.paintingscollectors.model.dto.AddPaintingDTO;
import com.paintingscollectors.model.entity.PaintingEntity;
import com.paintingscollectors.model.entity.StyleEntity;
import com.paintingscollectors.model.entity.UserEntity;
import com.paintingscollectors.repository.PaintingRepository;
import com.paintingscollectors.repository.UserRepository;
import com.paintingscollectors.util.LoggedUser;
import org.apache.catalina.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class PaintingService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final StyleService styleService;
    private final PaintingRepository paintingRepository;
    private final LoggedUser loggedUser;

    public PaintingService(UserService userService, UserRepository userRepository, StyleService styleService, PaintingRepository paintingRepository, LoggedUser loggedUser) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.styleService = styleService;
        this.paintingRepository = paintingRepository;
        this.loggedUser = loggedUser;
    }

    public void addPainting(AddPaintingDTO addPaintingDTO, Long id) {
        UserEntity userEntity = this.userService.findUserById(id).orElse(null);
        StyleEntity styleEntity = this.styleService.findStyle(addPaintingDTO.getStyle());
        PaintingEntity paintingEntity = new PaintingEntity();
        paintingEntity.setStyle(styleEntity);
        paintingEntity.setOwner(userEntity);
        paintingEntity.setAuthor(addPaintingDTO.getAuthor());
        paintingEntity.setFavorite(false);
        paintingEntity.setImageURL(addPaintingDTO.getImage());
        paintingEntity.setName(addPaintingDTO.getName());
        paintingEntity.setVotes(0);
        userEntity.getPaintings().add(paintingEntity);
        paintingRepository.save(paintingEntity);
        userRepository.save(userEntity);

    }
    public boolean addPaintingToFavorites(Long id) {
        UserEntity userEntity = this.userService.findUserById(loggedUser.getId()).orElse(null);
        PaintingEntity paintingEntity = this.paintingRepository.findById(id).orElse(null);
        if (userEntity.getFavoritePaintings().contains(paintingEntity)) {
            return false;
        }
        if (userEntity != null && paintingEntity != null) {
            paintingEntity.setFavorite(true);
            this.paintingRepository.save(paintingEntity);
            userEntity.getFavoritePaintings().add(paintingEntity);
            userRepository.save(userEntity);
            return true;
        }
        return false;
    }

    public List<PaintingEntity> getMostVotedPaintings() {
        return this.paintingRepository.findTop2MostVotedPaintings(PageRequest.of(0, 2));
    }

    public Set<PaintingEntity> getMyPaintings() {
        UserEntity userEntity = this.userService.findUserById(loggedUser.getId()).orElse(null);
        return userEntity.getPaintings();
    }
    public Set<PaintingEntity> getOtherPaintings() {
        return this.paintingRepository.findAllByOwnerUsernameNot(loggedUser.getUsername());
    }
@Transactional
    public boolean removePaintingById(Long id) {
        PaintingEntity paintingById = this.paintingRepository.findById(id).orElse(null);
        if (paintingById != null && !paintingById.isFavorite()) {
            paintingById.getOwner().getPaintings().remove(paintingById);
            paintingById.setStyle(null);
            Set<UserEntity> allUsers= this.userRepository.findAllBy();
            for (UserEntity user : allUsers) {
                if (user.getRatedPaintings().contains(paintingById)) {
                    user.getRatedPaintings().remove(paintingById);
                }
            }
            userRepository.save(paintingById.getOwner());
            paintingById.setOwner(null);
            paintingRepository.delete(paintingById);
            return true;
        }
        return false;
    }

    public boolean votePaintingById(Long id) {
        PaintingEntity paintingById = this.paintingRepository.findById(id).orElse(null);
        UserEntity userEntity = this.userService.findUserById(loggedUser.getId()).orElse(null);
        if (paintingById == null) {
            return false;
        }

        if (userEntity.getRatedPaintings().contains(paintingById)){
            return false;
        }
        userEntity.getRatedPaintings().add(paintingById);
        userRepository.save(userEntity);
        int votes = paintingById.getVotes();
        paintingById.setVotes(++votes);
        paintingRepository.save(paintingById);
        return true;
    }
    @Transactional
    public void removeFromFavorites(Long id) {
        PaintingEntity paintingById = this.paintingRepository.findById(id).orElse(null);
        UserEntity userEntity = this.userService.findUserById(loggedUser.getId()).orElse(null);
        paintingById.setFavorite(false);
        paintingRepository.save(paintingById);
        Set<PaintingEntity> favoritePaintings = userEntity.getFavoritePaintings();
        favoritePaintings.remove(paintingById);
        this.userRepository.save(userEntity);

    }
}
