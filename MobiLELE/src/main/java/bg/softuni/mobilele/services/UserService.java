package bg.softuni.mobilele.services;

import bg.softuni.mobilele.config.Mapper;
import bg.softuni.mobilele.models.dtos.UserLoginDTO;
import bg.softuni.mobilele.models.dtos.UserRegisterDTO;
import bg.softuni.mobilele.models.entities.User;
import bg.softuni.mobilele.repositories.UserRepository;
import bg.softuni.mobilele.user.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private Logger logger = LoggerFactory.getLogger(UserService.class);
    private CurrentUser currentUser;
    private PasswordEncoder passwordEncoder;
    private final Mapper mapper;

    public UserService(UserRepository userRepository,
                       CurrentUser currentUser,
                       PasswordEncoder passwordEncoder,
                       Mapper mapper) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    public boolean login(UserLoginDTO dto) {
        User byEmail = userRepository.findByEmail(dto.getUsername()).orElse(null);
        if (byEmail == null) {
            logger.info("User with [{}] not found.", dto.getUsername());
            return false;
        }
        String rawPassword = dto.getPassword();
        String hashedPassword = byEmail.getPassword();
        boolean success = passwordEncoder.matches(rawPassword, hashedPassword);
        if (success) {
            login(byEmail);
        } else {
            logout();
        }
        return success;
    }

    public void login(User user) {
        currentUser
                .setLoggedIn(true)
                .setFirstName(user.getFirstName())
                .setEmail(user.getEmail());
    }

    public void logout() {
        currentUser.clear();
    }

    public void registerAndLogin(UserRegisterDTO userRegisterDTO) {
        User user = mapper.userDtoToUser(userRegisterDTO);
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        this.userRepository.save(user);
        login(user);

    }
}
