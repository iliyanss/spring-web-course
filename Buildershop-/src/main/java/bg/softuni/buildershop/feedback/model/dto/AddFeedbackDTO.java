package bg.softuni.buildershop.feedback.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddFeedbackDTO {

    private Long id;

    @Size(min = 10, max = 200)
    @NotNull
    private String text;

    public @Size(min = 10, max = 200) @NotNull String getText() {
        return text;
    }

    public AddFeedbackDTO setText(@Size(min = 10, max = 200) @NotNull String text) {
        this.text = text;
        return this;
    }

    public AddFeedbackDTO() {

    }

    public Long getId() {
        return id;
    }

    public AddFeedbackDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
