package bg.softuni.buildershop.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "feedback.api")
public class FeedbackApiConfig {
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public FeedbackApiConfig setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }
}
