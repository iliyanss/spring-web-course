package bg.softuni.buildershop.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddFeedbackDTO {

    private Long id;

    @Size(min = 10, max = 700, message = "The length of the text should be between 10 and 700 symbols")
    @NotNull
    private String text;

    public AddFeedbackDTO() {

    }

    public @Size(min = 10, max = 700, message = "The length of the text should be between 10 and 700 symbols") @NotNull String getText() {
        return text;
    }

    public AddFeedbackDTO setText(@Size(min = 10, max = 700, message = "The length of the text should be between 10 and 700 symbols") @NotNull String text) {
        this.text = text;
        return this;
    }

    public Long getId() {
        return id;
    }

    public AddFeedbackDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
