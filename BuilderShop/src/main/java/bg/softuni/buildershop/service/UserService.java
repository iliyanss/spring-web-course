package bg.softuni.buildershop.service;

import bg.softuni.buildershop.model.dto.RegisterDTO;
import bg.softuni.buildershop.model.dto.UserDTO;
import bg.softuni.buildershop.model.entity.UserEntity;
import bg.softuni.buildershop.model.entity.UserRoleEntity;
import bg.softuni.buildershop.model.enums.UserRoleEnum;
import bg.softuni.buildershop.repository.UserRepository;
import bg.softuni.buildershop.repository.UserRoleRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserDetailsService builderUserDetailsService;
    private final UserRoleRepository roleRepository;

    public UserService(UserRepository userRepository,
                       PasswordEncoder encoder,
                       UserDetailsService builderUserDetailsService, UserRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.builderUserDetailsService = builderUserDetailsService;
        this.roleRepository = roleRepository;
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
        UserRoleEntity userRole = this.roleRepository.findByRole(UserRoleEnum.USER);
        user.getRoles().add(userRole);
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

    public Authentication login(String username) {
        UserDetails userDetails = builderUserDetailsService.loadUserByUsername(username);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        return auth;
    }
}
