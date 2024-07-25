package bg.softuni.buildershop;

import bg.softuni.buildershop.model.dto.LoginDTO;
import bg.softuni.buildershop.model.entity.UserEntity;
import bg.softuni.buildershop.model.entity.UserRoleEntity;
import bg.softuni.buildershop.model.enums.UserRoleEnum;
import bg.softuni.buildershop.repository.UserRepository;
import bg.softuni.buildershop.repository.UserRoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserEntity testUser;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        testUser = createTestUser();
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void testLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("/login"));
    }

    @Test
    void testLoginSuccess() throws Exception {
        mockMvc.perform(post("/login")
                        .param("username", testUser.getUsername())
                        .param("password", testUser.getPassword())
                        .with(csrf()))
                .andExpect(status().isFound());
    }
    @Test
    void testLoginFailure() throws Exception {
        mockMvc.perform(formLogin().user("invalidUser").password("wrongPassword"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/login-error"));
    }
    private UserEntity createTestUser() {

        UserEntity user = new UserEntity();
        user.setUsername("Admin");
        user.setPassword(passwordEncoder.encode("Adminov1"));
        user.setEmail("iliyan.ss@outlook.com");
        user.setRoles(userRoleRepository.findAll());
        userRepository.save(user);
        return user;
    }
}
