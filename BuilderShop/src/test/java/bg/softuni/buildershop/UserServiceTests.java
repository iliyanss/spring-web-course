package bg.softuni.buildershop;

import bg.softuni.buildershop.model.dto.*;
import bg.softuni.buildershop.model.entity.ProductEntity;
import bg.softuni.buildershop.model.entity.UserEntity;
import bg.softuni.buildershop.model.entity.UserRoleEntity;
import bg.softuni.buildershop.model.enums.UserRoleEnum;
import bg.softuni.buildershop.repository.ProductRepository;
import bg.softuni.buildershop.repository.UserRepository;
import bg.softuni.buildershop.repository.UserRoleRepository;
import bg.softuni.buildershop.service.CategoryService;
import bg.softuni.buildershop.service.ExRateService;
import bg.softuni.buildershop.service.ProductService;
import bg.softuni.buildershop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTests {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder encoder;
    @Mock
    private UserDetailsService builderUserDetailsService;
    @Mock
    private UserRoleRepository roleRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private UserService userService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // UserService tests

    @Test
    public void testConstructor() {
        UserService userService1 = new UserService(userRepository, encoder, builderUserDetailsService, roleRepository, modelMapper, productRepository);
        assertNotNull(userService1);
    }

    @Test
    public void testFindUserByEmail_UserExists() {
        String email = "email";
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);

        doReturn(Optional.of(userEntity)).when(userRepository).findByEmail(email);
        when(modelMapper.map(userEntity, UserDTO.class)).thenReturn(new UserDTO().setEmail(email));

        UserDTO userDTO = userService.findUserByEmail(email);
        assertNotNull(userDTO);
        assertEquals(email, userDTO.getEmail());
    }

    @Test
    public void testFindUserByEmail_UserNotExists() {
        String email = "nonexistent@example.com";
        doReturn(Optional.empty()).when(userRepository).findByEmail(email);

        UserDTO userDTO = userService.findUserByEmail(email);
        assertNull(userDTO);
    }

    @Test
    public void testFindUserByUsername_UserExists() {
        String username = "username";
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);

        doReturn(Optional.of(userEntity)).when(userRepository).findByUsername(username);
        when(modelMapper.map(userEntity, UserDTO.class)).thenReturn(new UserDTO().setUsername(username));

        UserDTO userDTO = userService.findUserByUsername(username);
        assertNotNull(userDTO);
        assertEquals(username, userDTO.getUsername());
    }

    @Test
    public void testFindUserByUsername_UserNotExists() {
        String username = "nonexistent";
        doReturn(Optional.empty()).when(userRepository).findByUsername(username);

        UserDTO userDTO = userService.findUserByUsername(username);
        assertNull(userDTO);
    }

    @Test
    public void testMapUser() {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setEmail("email");
        registerDTO.setPassword("password");
        registerDTO.setId(1L);
        registerDTO.setUsername("username");
        registerDTO.setConfirmPassword("password");

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(registerDTO.getEmail());
        userEntity.setUsername(registerDTO.getUsername());
        userEntity.setPassword(registerDTO.getPassword());

        when(modelMapper.map(registerDTO, UserEntity.class)).thenReturn(userEntity);
        when(encoder.encode(registerDTO.getPassword())).thenReturn("encodedPassword");
        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setRole(UserRoleEnum.USER);
        doReturn(userRole).when(roleRepository).findByRole(UserRoleEnum.USER);

        UserEntity mappedUser = userService.mapUser(registerDTO);

        assertNotNull(mappedUser);
        assertEquals("encodedPassword", mappedUser.getPassword());
        assertEquals(1, mappedUser.getRoles().size());
        assertEquals(UserRoleEnum.USER, mappedUser.getRoles().get(0).getRole());
    }

    @Test
    public void testFindUserById() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        doReturn(Optional.of(userEntity)).when(userRepository).findById(1L);

        Optional<UserEntity> foundUser = userService.findUserById(1L);
        assertTrue(foundUser.isPresent());
        assertEquals(1L, foundUser.get().getId());
    }

    @Test
    public void testGetUserByUsername() {
        String username = "username";
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);

        doReturn(Optional.of(userEntity)).when(userRepository).findByUsername(username);

        UserEntity foundUser = userService.getUserByUsername(username);
        assertNotNull(foundUser);
        assertEquals(username, foundUser.getUsername());
    }

    @Test
    public void testRegister() {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setEmail("email");
        registerDTO.setPassword("password");
        registerDTO.setId(1L);
        registerDTO.setUsername("username");
        registerDTO.setConfirmPassword("password");

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(registerDTO.getEmail());
        userEntity.setUsername(registerDTO.getUsername());
        userEntity.setPassword(registerDTO.getPassword());

        when(modelMapper.map(registerDTO, UserEntity.class)).thenReturn(userEntity);
        when(encoder.encode(registerDTO.getPassword())).thenReturn("encodedPassword");
        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setRole(UserRoleEnum.USER);
        doReturn(userRole).when(roleRepository).findByRole(UserRoleEnum.USER);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        userService.register(registerDTO);

        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    public void testGetUserIdFromPrincipal() {
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("username");

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("username");
        userEntity.setId(1L);

        when(userRepository.findByUsername("username")).thenReturn(Optional.of(userEntity));

        Long userId = userService.getUserIdFromPrincipal(principal);
        assertEquals(1L, userId);
    }

    @Test
    public void testFindAllUsersExceptAdmin() {
        UserEntity user1 = new UserEntity();
        user1.setUsername("user1");
        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setRole(UserRoleEnum.USER);
        user1.setRoles(List.of(userRole));

        UserEntity user2 = new UserEntity();
        user2.setUsername("user2");
        UserRoleEntity adminRole = new UserRoleEntity();
        adminRole.setRole(UserRoleEnum.ADMIN);
        user2.setRoles(List.of(adminRole));

        when(userRepository.findAll()).thenReturn(List.of(user1, user2));
        when(modelMapper.map(user1, UserDTO.class)).thenReturn(new UserDTO().setUsername("user1"));

        List<UserDTO> users = userService.findAllUsersExceptAdmin();
        assertEquals(1, users.size());
        assertEquals("user1", users.get(0).getUsername());
    }

    @Test
    public void testDeleteUser() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        ProductEntity product = new ProductEntity();
        product.setAuthor(user);
        user.setProducts(new ArrayList<>(List.of(product)));

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.deleteUser(1L);

        assertTrue(user.getProducts().isEmpty());
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void testUpdateUsername() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername("oldUsername");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.updateUsername(1L, "newUsername");

        assertEquals("newUsername", user.getUsername());
        verify(userRepository, times(1)).save(user);
    }




}
