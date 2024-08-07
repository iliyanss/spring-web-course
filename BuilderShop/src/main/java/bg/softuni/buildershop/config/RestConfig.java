package bg.softuni.buildershop.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {

    @Bean("forexRestClient")
    public RestClient forexRestClient() {
        return RestClient.create();
    }
    @Bean("feedbackRestClient")
    public RestClient feedbackRestClient(FeedbackApiConfig feedbackApiConfig) {
        return RestClient
                .builder()
                .baseUrl(feedbackApiConfig.getBaseUrl())
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
