package bg.softuni.buildershop;

import bg.softuni.buildershop.model.dto.RegisterDTO;
import bg.softuni.buildershop.repository.UserRepository;
import bg.softuni.buildershop.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private RegisterDTO registerDTO;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll(); // Clear the repository before each test
        registerDTO = createUser();
    }

    @Test
    void testRegisterPage() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    void testRegisterSuccess() throws Exception {
        long initialUserCount = userRepository.count();

        mockMvc.perform(
                        post("/register")
                                .param("username", registerDTO.getUsername())
                                .param("email", registerDTO.getEmail())
                                .param("password", registerDTO.getPassword())
                                .param("confirmPassword", registerDTO.getConfirmPassword())
                                .with(csrf())
                )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/home"));

        Assertions.assertEquals(initialUserCount + 1, userRepository.count());
        Assertions.assertNotNull(userRepository.findByUsername("testUser").get());
        Assertions.assertEquals("test@test.com", userRepository.findByUsername("testUser").get().getEmail());
    }

    @Test
    void testRegisterPasswordMismatch() throws Exception {
        mockMvc.perform(
                        post("/register")
                                .param("username", registerDTO.getUsername())
                                .param("email", registerDTO.getEmail())
                                .param("password", "password123")
                                .param("confirmPassword", "differentPassword")
                                .with(csrf())
                )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/register"))
                .andExpect(flash().attributeExists("registerDTO"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.registerDTO"));

        Assertions.assertEquals(0, userRepository.count());
    }

    @Test
    void testRegisterWithValidationErrors() throws Exception {
        mockMvc.perform(
                        post("/register")
                                .param("username", "") // Invalid username
                                .param("email", "invalidemail") // Invalid email
                                .param("password", "short") // Invalid password
                                .param("confirmPassword", "short")
                                .with(csrf())
                )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/register"))
                .andExpect(flash().attributeExists("registerDTO"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.registerDTO"));

        Assertions.assertEquals(0, userRepository.count());
    }

    private RegisterDTO createUser() {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setUsername("testUser");
        registerDTO.setEmail("test@test.com");
        registerDTO.setPassword("Borovaneca1");
        registerDTO.setConfirmPassword("Borovaneca1");
        return registerDTO;
    }
}
