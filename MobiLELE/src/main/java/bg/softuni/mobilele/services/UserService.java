package bg.softuni.mobilele.services;

import bg.softuni.mobilele.models.dtos.UserLoginDTO;
import bg.softuni.mobilele.models.dtos.UserRegistrationDTO;
import bg.softuni.mobilele.models.entities.UserEntity;
import bg.softuni.mobilele.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    public UserService(ModelMapper modelMapper,
                       PasswordEncoder passwordEncoder,
                       UserRepository userRepository,
                       CurrentUser currentUser) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    public void registerUser(UserRegistrationDTO userRegistration) {
        userRepository.save(map(userRegistration));
    }

    public boolean login(UserLoginDTO userLoginDTO) {

        UserEntity userEntity = userRepository
                .findByEmail(userLoginDTO.email())
                .orElse(null);

        if (userLoginDTO.password() == null ||
                userEntity == null ||
                userEntity.getPassword() == null) {
            return false;
        }

        boolean success = passwordEncoder.matches(userLoginDTO.password(), userEntity.getPassword());

        if (success) {
            currentUser.setLoggedIn(true);
            currentUser.setFullName(userEntity.getFirstName() + " " + userEntity.getLastName());
        } else {
            currentUser.clean();
        }

        return false;
    }

    private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
        UserEntity mappedEntity = modelMapper.map(userRegistrationDTO, UserEntity.class);

        mappedEntity.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        return mappedEntity;
    }

    public void logout() {
        currentUser.clean();
    }
}
