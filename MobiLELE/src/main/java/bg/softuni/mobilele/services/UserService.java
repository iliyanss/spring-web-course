package bg.softuni.mobilele.services;

import bg.softuni.mobilele.models.dtos.UserLoginDTO;
import bg.softuni.mobilele.models.dtos.UserRegistrationDTO;
import bg.softuni.mobilele.models.entities.UserEntity;
import bg.softuni.mobilele.models.entities.UserRoleEntity;
import bg.softuni.mobilele.models.enums.UserRoleEnum;
import bg.softuni.mobilele.repositories.RoleRepository;
import bg.softuni.mobilele.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserService(ModelMapper modelMapper,
                       PasswordEncoder passwordEncoder,
                       UserRepository userRepository, RoleRepository roleRepository
    ) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void registerUser(UserRegistrationDTO userRegistration) {
        userRepository.save(map(userRegistration));
    }

    private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
        UserEntity mappedEntity = modelMapper.map(userRegistrationDTO, UserEntity.class);
        mappedEntity.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        UserRoleEntity userRole = this.roleRepository.findByRole(UserRoleEnum.USER);
        mappedEntity.getRoles().add(userRole);

        return mappedEntity;
    }

}
