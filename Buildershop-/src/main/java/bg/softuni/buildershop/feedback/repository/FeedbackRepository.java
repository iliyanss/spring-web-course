package bg.softuni.buildershop.feedback.repository;

import bg.softuni.buildershop.feedback.model.entity.FeedBackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedBackEntity, Long> {

}
