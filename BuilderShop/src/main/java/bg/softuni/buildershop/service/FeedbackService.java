package bg.softuni.buildershop.service;

import bg.softuni.buildershop.config.FeedbackApiConfig;
import bg.softuni.buildershop.model.dto.AddFeedbackDTO;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class FeedbackService {
    private final RestClient restClient;
    private final Logger logger = LoggerFactory.getLogger(FeedbackService.class);
    private final FeedbackApiConfig feedbackApiConfig;

    public FeedbackService(@Qualifier("feedbackRestClient") RestClient restClient, FeedbackApiConfig feedbackApiConfig) {
        this.restClient = restClient;
        this.feedbackApiConfig = feedbackApiConfig;
    }

    public void createFeedback(AddFeedbackDTO feedback) {
        logger.info("Creating new createFeedback...");
        restClient
                .post()
                .uri(feedbackApiConfig.getBaseUrl() + "/feedback")
                .body(feedback)
                .retrieve();
    }

    public List<AddFeedbackDTO> getAllMessages() {
        logger.info("Getting all feedbacks...");
        return restClient
                .get()
                .uri(feedbackApiConfig.getBaseUrl() + "/feedback/all")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
@Transactional
    public void removeFeedback(Long id) {
        restClient
                .delete()
                .uri(feedbackApiConfig.getBaseUrl() + "/feedback/remove/{id}", id) // Properly include id in the URI
                .retrieve()
                .toBodilessEntity();
    }
}
