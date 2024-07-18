package bg.softuni.buildershop.repository;

import bg.softuni.buildershop.model.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    boolean existsByMessageText(String messageText);
}