package bg.softuni.buildershop;

import bg.softuni.buildershop.model.dto.MessageDTO;
import bg.softuni.buildershop.model.entity.UserEntity;
import bg.softuni.buildershop.service.MessageService;
import bg.softuni.buildershop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @MockBean
    private UserService userService;

    @MockBean
    private Model model;

    private UserEntity testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new UserEntity();
        testUser.setId(1L);
        testUser.setUsername("testUser");
        testUser.setEmail("test@test.com");
    }

    @Test
    @WithMockUser(username = "testUser")
    void testShowContactForm() throws Exception {
        mockMvc.perform(get("/messages/contact"))
                .andExpect(status().isOk())
                .andExpect(view().name("contact"));
    }

    @Test
    @WithMockUser(username = "testUser")
    void testSendMessageValid() throws Exception {
        when(userService.getUserIdFromPrincipal(any(Principal.class))).thenReturn(1L);
        when(userService.findUserById(1L)).thenReturn(Optional.of(testUser));

        mockMvc.perform(post("/messages/send")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .param("name", "Test User")
                        .param("email", "test@test.com")
                        .param("messageText", "Test message"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/messages/contact?success"));

        verify(messageService, times(1)).sendMessage(any(MessageDTO.class), any(Principal.class));
    }

    @Test
    @WithMockUser(username = "testUser")
    void testSendMessageInvalidEmail() throws Exception {
        when(userService.getUserIdFromPrincipal(any(Principal.class))).thenReturn(1L);
        when(userService.findUserById(1L)).thenReturn(Optional.of(testUser));

        mockMvc.perform(post("/messages/send")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .param("email", "wrongemail@test.com")
                        .param("message", "Test message"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/messages/contact"));

        verify(messageService, times(0)).sendMessage(any(MessageDTO.class), any(Principal.class));
    }

    @Test
    @WithMockUser(username = "testUser")
    void testSendMessageWithBindingErrors() throws Exception {
        when(userService.getUserIdFromPrincipal(any(Principal.class))).thenReturn(1L);
        when(userService.findUserById(1L)).thenReturn(Optional.of(testUser));

        mockMvc.perform(post("/messages/send")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .param("email", "")
                        .param("message", ""))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/messages/contact"));

        verify(messageService, times(0)).sendMessage(any(MessageDTO.class), any(Principal.class));
    }

    @Test
    @WithMockUser(username = "testUser", roles = "ADMIN")
    void testGetAllMessages() throws Exception {
        when(messageService.getAllMessages()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/messages/all-messages"))
                .andExpect(status().isOk())
                .andExpect(view().name("all-messages"))
                .andExpect(model().attributeExists("messages"));

        verify(messageService, times(1)).getAllMessages();
    }

    @Test
    @WithMockUser(username = "testUser", roles = "ADMIN")
    void testRemoveMessage() throws Exception {
        mockMvc.perform(delete("/messages/remove/1")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/messages/all-messages"));

        verify(messageService, times(1)).removeMessage(1L);
    }
}
