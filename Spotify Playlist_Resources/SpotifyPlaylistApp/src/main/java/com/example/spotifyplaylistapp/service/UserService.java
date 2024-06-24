package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.RegisterDTO;
import com.example.spotifyplaylistapp.model.dto.UserDTO;
import com.example.spotifyplaylistapp.model.entity.UserEntity;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final LoggedUser loggedUser;
    private final HttpSession session;

    public UserService(UserRepository userRepository, PasswordEncoder encoder, LoggedUser loggedUser, HttpSession session) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.loggedUser = loggedUser;
        this.session = session;
    }

    public UserDTO findUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return null;
        }

        return this.mapUserDTO(user);
    }

    public UserDTO findUserByUsername(String username) {
        UserEntity user = this.userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return null;
        }

        return this.mapUserDTO(user);
    }
    private UserDTO mapUserDTO(UserEntity user) {
        return new UserDTO()
                .setId(user.getId())
                .setEmail(user.getEmail())
                .setUsername(user.getUsername());
    }
    private UserEntity mapUser(RegisterDTO registerDTO) {
        UserEntity user = new UserEntity();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(encoder.encode(registerDTO.getPassword()));
        return user;
    }

    public boolean checkCredentials(String username, String password) {
        UserEntity user = this.getUserByUsername((username));

        if (user == null) {
            return false;
        }

        return encoder.matches(password, user.getPassword());
    }
    public Optional<UserEntity> findUserById(Long id) {
        return userRepository.findById(id);

    }

    private UserEntity getUserByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    public void register(RegisterDTO registerDTO) {
        this.userRepository.save(mapUser(registerDTO));
    }

    public void login(String username) {
        UserEntity user = this.getUserByUsername(username);
        this.loggedUser.setUsername(user.getUsername());
        this.loggedUser.setId(user.getId());

    }
    public void logout() {
        this.session.invalidate();
        this.loggedUser.setId(null);
        this.loggedUser.setUsername(null);
    }
}
