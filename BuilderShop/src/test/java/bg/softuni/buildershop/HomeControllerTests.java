package bg.softuni.buildershop;

import bg.softuni.buildershop.model.entity.ProductEntity;
import bg.softuni.buildershop.model.entity.UserEntity;
import bg.softuni.buildershop.service.ProductService;
import bg.softuni.buildershop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private ProductService productService;

    @MockBean
    private Model model;

    private UserEntity testUser;

    @BeforeEach
    void setUp() {
        testUser = new UserEntity();
        testUser.setId(1L);
        testUser.setUsername("testUser");
        testUser.setEmail("test@test.com");
        testUser.setProducts(Collections.emptyList());

        when(userService.getUserIdFromPrincipal(any(Principal.class))).thenReturn(1L);
        when(userService.findUserById(1L)).thenReturn(Optional.of(testUser));
    }

    @Test
    void testIndexAnonymous() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    @WithMockUser(username = "testUser")
    void testIndexAuthenticated() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/home"));
    }

    @Test
    @WithMockUser(username = "testUser")
    void testHome() throws Exception {
        when(userService.findUserById(anyLong())).thenReturn(Optional.of(testUser));
        Principal mockPrincipal = () -> "testUser";
        mockMvc.perform(get("/home").principal(mockPrincipal))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }


    @Test
    void testAbout() throws Exception {
        mockMvc.perform(get("/about"))
                .andExpect(status().isOk())
                .andExpect(view().name("about"));
    }
}
