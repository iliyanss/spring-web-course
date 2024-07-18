package bg.softuni.buildershop.service;

import bg.softuni.buildershop.model.dto.RegisterDTO;
import bg.softuni.buildershop.model.dto.UserDTO;
import bg.softuni.buildershop.model.entity.ProductEntity;
import bg.softuni.buildershop.model.entity.UserEntity;
import bg.softuni.buildershop.model.entity.UserRoleEntity;
import bg.softuni.buildershop.model.enums.UserRoleEnum;
import bg.softuni.buildershop.repository.ProductRepository;
import bg.softuni.buildershop.repository.UserRepository;
import bg.softuni.buildershop.repository.UserRoleRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserDetailsService builderUserDetailsService;
    private final UserRoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public UserService(UserRepository userRepository,
                       PasswordEncoder encoder,
                       UserDetailsService builderUserDetailsService,
                       UserRoleRepository roleRepository,
                       ModelMapper modelMapper, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.builderUserDetailsService = builderUserDetailsService;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    public UserDTO findUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return null;
        }

        return this.modelMapper.map(user, UserDTO.class);
    }

    public UserDTO findUserByUsername(String username) {
        UserEntity user = this.userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return null;
        }

        return this.modelMapper.map(user, UserDTO.class);
    }

    private UserEntity mapUser(RegisterDTO registerDTO) {
        UserEntity user = this.modelMapper.map(registerDTO, UserEntity.class);
        user.setPassword(encoder.encode(registerDTO.getPassword()));
        UserRoleEntity userRole = this.roleRepository.findByRole(UserRoleEnum.USER);
        user.getRoles().add(userRole);
        return user;
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
    public Long getUserIdFromPrincipal(Principal principal) {
        String username = principal.getName();
        UserEntity user = userRepository.findByUsername(username).orElse(null);

        return user.getId();
    }

    public List<UserDTO> findAllUsersExceptAdmin() {
        return userRepository.findAll().stream()
                .filter(user -> user.getRoles().stream().noneMatch(role -> role.getRole() == UserRoleEnum.ADMIN))
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();
    }

    @Transactional
    public void deleteUser(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Iterator<ProductEntity> productIterator = user.getProducts().iterator();
        while (productIterator.hasNext()) {
            ProductEntity product = productIterator.next();
            product.getUsersWhoFavorited().clear();
            productIterator.remove();
            product.setAuthor(null);
            productRepository.delete(product);
        }

        Iterator<ProductEntity> favoriteIterator = user.getFavoriteProducts().iterator();
        while (favoriteIterator.hasNext()) {
            favoriteIterator.next();
            favoriteIterator.remove();
        }
        user.getProducts().clear();
        user.getFavoriteProducts().clear();
        userRepository.delete(user);
    }


    @Transactional
    public void updateUsername(Long userId, String newUsername) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setUsername(newUsername);
        userRepository.save(user);
    }
}
