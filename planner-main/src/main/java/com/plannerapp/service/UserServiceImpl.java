package com.plannerapp.service;

import com.plannerapp.model.dto.*;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.UserRepo;
import com.plannerapp.util.LoggedUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final LoggedUser loggedUser;
    private final HttpSession session;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder encoder, LoggedUser loggedUser, HttpSession session) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.loggedUser = loggedUser;
        this.session = session;
    }

    public UserDTO findUserByUsername(String username) {
        User user = this.getUserByUsername(username);
        if (user == null) {
            return null;
        }

        return this.mapUserDTO(user);
    }

    public UserDTO findUserByEmail(String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        if (user == null) {
            return null;
        }

        return this.mapUserDTO(user);
    }

    public boolean checkCredentials(String username, String password) {
        User user = this.getUserByUsername(username);

        if (user == null) {
            return false;
        }

        return encoder.matches(password, user.getPassword());
    }

    public void login(String username) {
        User user = this.getUserByUsername(username);
        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
    }

    public void register(RegisterDTO registerDTO) {
        this.userRepo.save(this.mapUser(registerDTO));
        this.login(registerDTO.getUsername());
    }

    public void logout() {
        this.session.invalidate();
        this.loggedUser.setId(null);
        this.loggedUser.setUsername(null);
    }

    private User getUSerById(Long userId) {
        return this.userRepo.findById(userId).orElseThrow();
    }

    private User getUserByUsername(String username) {
        return this.userRepo.findByUsername(username).orElse(null);
    }

    private User mapUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(encoder.encode(registerDTO.getPassword()));
        return user;
    }

    private UserDTO mapUserDTO(User user) {
        return new UserDTO()
                .setId(user.getId())
                .setEmail(user.getEmail())
                .setUsername(user.getUsername());
    }

    public void initAdmin() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(encoder.encode("1234"));
        admin.setEmail("admin@abv.bg");
        userRepo.save(admin);
    }

    public Optional<User> findUserById(Long id) {
        return userRepo.findById(id);

    }

    public void initTest() {
        User test = new User();
        test.setUsername("testUser");
        test.setPassword(encoder.encode("1234"));
        test.setEmail("test@abv.bg");
        userRepo.save(test);
    }

    public Set<Task> getAssignedTasksFromCurrentUser(Long id) {
        User user = userRepo.findById(id).orElse(null);
        return user.getAssignedTasks();
    }

    public Set<UsersWithTasksDTO> getTasksFromOtherUsers(Long id) {
        Set<User> allOtherUsers = userRepo.findAllByIdIsNot(id);

        return mapToAllOtherUsersOffersDTO(allOtherUsers);
    }

    private Set<UsersWithTasksDTO> mapToAllOtherUsersOffersDTO(Set<User> allOtherUsers) {
        Set<UsersWithTasksDTO> collect = allOtherUsers
                .stream()
                .map(e -> {
                    UsersWithTasksDTO currentDTO = new UsersWithTasksDTO();

                    Set<Task> tasks = e.getAssignedTasks();
                    Set<TaskDTO> offersDTO = tasks
                            .stream()
                            .map(currentOffer -> {
                                TaskDTO taskDTO = new TaskDTO();
                                taskDTO
                                        .setId(currentOffer.getId())
                                        .setPriority(currentOffer.getPriority().getPriorityName())
                                        .setDueDate(currentOffer.getDueDate())
                                        .setDescription(currentOffer.getDescription());
                                return taskDTO;
                            }).collect(Collectors.toSet());

                    currentDTO
                            .setId(e.getId())
                            .setUsername(e.getUsername())
                            .setOffers(offersDTO);
                    return currentDTO;
                })
                .collect(Collectors.toSet());

        return collect;
    }


}
