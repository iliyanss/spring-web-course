package bg.softuni.buildershop;

import bg.softuni.buildershop.model.dto.MessageDTO;
import bg.softuni.buildershop.model.entity.MessageEntity;
import bg.softuni.buildershop.model.entity.UserEntity;
import bg.softuni.buildershop.repository.MessageRepository;
import bg.softuni.buildershop.repository.UserRepository;
import bg.softuni.buildershop.service.MessageService;
import bg.softuni.buildershop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MessageServiceTests {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private UserService userService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MessageService messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendMessage_Success() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setEmail("user@example.com");
        messageDTO.setMessageText("Test Message");

        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("user");

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setEmail("user@example.com");

        when(userService.getUserIdFromPrincipal(principal)).thenReturn(1L);
        when(userService.findUserById(1L)).thenReturn(Optional.of(user));
        when(messageRepository.existsByMessageText("Test Message")).thenReturn(false);

        MessageEntity messageEntity = new MessageEntity();
        when(modelMapper.map(messageDTO, MessageEntity.class)).thenReturn(messageEntity);

        messageService.sendMessage(messageDTO, principal);

        verify(messageRepository, times(1)).save(messageEntity);
    }

    @Test
    void testSendMessage_UserNotFound() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setEmail("user@example.com");
        messageDTO.setMessageText("Test Message");

        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("user");

        when(userService.getUserIdFromPrincipal(principal)).thenReturn(1L);
        when(userService.findUserById(1L)).thenReturn(Optional.empty());

        messageService.sendMessage(messageDTO, principal);

        verify(messageRepository, times(0)).save(any(MessageEntity.class));
    }

    @Test
    void testSendMessage_DuplicateMessage() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setEmail("user@example.com");
        messageDTO.setMessageText("Test Message");

        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("user");

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setEmail("user@example.com");

        when(userService.getUserIdFromPrincipal(principal)).thenReturn(1L);
        when(userService.findUserById(1L)).thenReturn(Optional.of(user));
        when(messageRepository.existsByMessageText("Test Message")).thenReturn(true);

        messageService.sendMessage(messageDTO, principal);

        verify(messageRepository, times(0)).save(any(MessageEntity.class));
    }

    @Test
    void testRemoveMessage() {
        Long messageId = 1L;

        messageService.removeMessage(messageId);

        verify(messageRepository, times(1)).deleteById(messageId);
    }

    @Test
    void testGetAllMessages() {
        MessageEntity messageEntity1 = new MessageEntity();
        messageEntity1.setMessageText("Message 1");

        MessageEntity messageEntity2 = new MessageEntity();
        messageEntity2.setMessageText("Message 2");

        List<MessageEntity> messageEntities = List.of(messageEntity1, messageEntity2);
        when(messageRepository.findAll()).thenReturn(messageEntities);

        MessageDTO messageDTO1 = new MessageDTO();
        messageDTO1.setMessageText("Message 1");

        MessageDTO messageDTO2 = new MessageDTO();
        messageDTO2.setMessageText("Message 2");

        when(modelMapper.map(messageEntity1, MessageDTO.class)).thenReturn(messageDTO1);
        when(modelMapper.map(messageEntity2, MessageDTO.class)).thenReturn(messageDTO2);

        List<MessageDTO> result = messageService.getAllMessages();

        assertEquals(2, result.size());
        assertTrue(result.contains(messageDTO1));
        assertTrue(result.contains(messageDTO2));
    }
}
