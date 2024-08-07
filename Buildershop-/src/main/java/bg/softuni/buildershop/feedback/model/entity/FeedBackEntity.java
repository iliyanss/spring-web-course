package bg.softuni.buildershop.feedback.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "feedbacks")
public class FeedBackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String text;

    public FeedBackEntity() {

    }

    public Long getId() {
        return id;
    }

    public FeedBackEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public FeedBackEntity setText(String text) {
        this.text = text;
        return this;
    }
}
