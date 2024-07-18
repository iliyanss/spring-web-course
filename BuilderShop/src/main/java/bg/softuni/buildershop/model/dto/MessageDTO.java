package bg.softuni.buildershop.model.dto;

import bg.softuni.buildershop.vallidation.annotation.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class MessageDTO {

    private Long id;

    @NotNull(message = "Name is mandatory")
    @Size(min = 1, max = 30, message = "Name must be between 1 and 30 characters")
    private String name;

    @Email(message = "Enter valid email!")
    @NotBlank(message = "Email cannot be empty!")
    private String email;

    @NotNull(message = "You must enter a message.")
    @Size(min = 1, max = 1000, message = "Message must be between 1 and 1000 characters")
    private String messageText;

    @NotNull
    private LocalDateTime createdAt;

    public MessageDTO() {

    }

    public Long getId() {
        return id;
    }

    public MessageDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public @NotNull(message = "Name is mandatory") @Size(min = 1, max = 30, message = "Name must be between 1 and 30 characters") String getName() {
        return name;
    }

    public MessageDTO setName(@NotNull(message = "Name is mandatory") @Size(min = 1, max = 30, message = "Name must be between 1 and 30 characters") String name) {
        this.name = name;
        return this;
    }

    public @Email(message = "Enter valid email!") @NotBlank(message = "Email cannot be empty!") String getEmail() {
        return email;
    }

    public MessageDTO setEmail(@Email(message = "Enter valid email!") @NotBlank(message = "Email cannot be empty!") String email) {
        this.email = email;
        return this;
    }

    public @NotNull(message = "You must enter a message.") @Size(min = 1, max = 1000, message = "Message must be between 1 and 1000 characters") String getMessageText() {
        return messageText;
    }

    public MessageDTO setMessageText(@NotNull(message = "You must enter a message.") @Size(min = 1, max = 1000, message = "Message must be between 1 and 1000 characters") String messageText) {
        this.messageText = messageText;
        return this;
    }

    public @NotNull LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public MessageDTO setCreatedAt(@NotNull LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}