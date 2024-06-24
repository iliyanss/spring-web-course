package com.likebookapp.service;

import com.likebookapp.model.entity.MoodEntity;
import com.likebookapp.model.entity.PostEntity;
import com.likebookapp.model.entity.UserEntity;
import com.likebookapp.model.entity.dto.AddPostDTO;
import com.likebookapp.repository.PostRepository;
import com.likebookapp.repository.UserRepository;
import com.likebookapp.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final MoodService moodService;
    private final LoggedUser loggedUser;


    public PostService(UserService userService, UserRepository userRepository, PostRepository postRepository, MoodService moodService, LoggedUser loggedUser) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.moodService = moodService;
        this.loggedUser = loggedUser;
    }

    public void addPost(AddPostDTO addpostDTO, Long id) {
        PostEntity postEntity = new PostEntity();
        MoodEntity moodEntity = this.moodService.findMood(addpostDTO.getMood());
        UserEntity userEntity = this.userService.findUserById(id).orElse(null);
        postEntity.setUser(userEntity);
        postEntity.setMood(moodEntity);
        postEntity.setContent(addpostDTO.getContent());
        userEntity.getAddedPosts().add(postEntity);
        postRepository.save(postEntity);
        userRepository.save(userEntity);
    }

    public List<PostEntity> getAllMyPosts() {
        return this.postRepository.findAllByUserUsername(loggedUser.getUsername());
    }

    public List<PostEntity> getOtherPosts() {

    return this.postRepository.findAllByUserUsernameNot(loggedUser.getUsername());
    }

    public boolean removePostById(Long id) {
        PostEntity postById = this.postRepository.findById(id).orElse(null);
        if (postById != null) {
            postById.setMood(null);
            postById.setContent(null);
            postById.getUser().getAddedPosts().remove(postById);
            userRepository.save(postById.getUser());
            postById.setUser(null);
            this.postRepository.delete(postById);
            return true;
        }
        return false;
    }

    public boolean likePostById(Long id) {
        PostEntity postById = this.postRepository.findById(id).orElse(null);
        UserEntity userEntity = this.userService.findUserById(loggedUser.getId()).orElse(null);
        if (postById != null) {
            postById.getUserLikes().add(userEntity);
            postRepository.save(postById);
            return true;
        }
        return false;
    }
}
