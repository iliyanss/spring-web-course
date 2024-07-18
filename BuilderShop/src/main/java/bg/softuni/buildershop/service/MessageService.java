package bg.softuni.buildershop.service;

import bg.softuni.buildershop.model.dto.MessageDTO;
import bg.softuni.buildershop.model.entity.MessageEntity;
import bg.softuni.buildershop.model.entity.UserEntity;
import bg.softuni.buildershop.repository.MessageRepository;
import bg.softuni.buildershop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.antlr.v4.runtime.misc.LogManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, UserService userService, ModelMapper modelMapper, UserRepository userRepository, UserRepository userRepository1) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository1;
    }

    public void sendMessage(MessageDTO messageDTO, Principal principal) {
        Long principalId = this.userService.getUserIdFromPrincipal(principal);
        UserEntity currentUser = this.userService.findUserById(principalId).orElse(null);

        if (currentUser == null || !messageDTO.getEmail().equals(currentUser.getEmail())) {
            return;
        }

        boolean duplicateExists = messageRepository.existsByMessageText(messageDTO.getMessageText());
        if (duplicateExists) {
            return;
        }

        MessageEntity message = modelMapper.map(messageDTO, MessageEntity.class);
        messageRepository.save(message);
    }

    @Transactional
    public void removeMessage(Long id) {
        messageRepository.deleteById(id);
    }

    public List<MessageDTO> getAllMessages() {
        return messageRepository.findAll().stream()
                .map(message -> modelMapper.map(message, MessageDTO.class))
                .collect(Collectors.toList());
    }
}
