package bg.softuni.buildershop.service;

import bg.softuni.buildershop.config.FeedbackApiConfig;
import bg.softuni.buildershop.model.dto.AddFeedbackDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

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
        logger.info("Creating new feedback...");
        restClient
                .post()
                .uri(feedbackApiConfig.getBaseUrl() + "/feedback")
                .body(feedback)
                .retrieve();
    }
}
