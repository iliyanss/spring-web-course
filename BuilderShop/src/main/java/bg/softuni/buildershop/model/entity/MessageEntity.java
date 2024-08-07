package bg.softuni.buildershop.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
@Entity
@Table(name = "messages")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Email
    private String email;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String messageText;
    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Constructors, getters, and setters
    public MessageEntity() {

    }

    public Long getId() {
        return id;
    }

    public MessageEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MessageEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public MessageEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getMessageText() {
        return messageText;
    }

    public MessageEntity setMessageText(String messageText) {
        this.messageText = messageText;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public MessageEntity setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
