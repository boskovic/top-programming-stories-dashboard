package org.example.topprogramingstoriesdashboard.scraper;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

public class NewStoriesClient implements NewStoriesGateway {
    public static final String NEW_STORIES_PATH = "/newstories.json";

    private final String baseUrl;

    public NewStoriesClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public List<Long> getNewStories() {
        return RestClient
                .builder()
                .baseUrl(baseUrl)
                .build()
                .get()
                .uri(NEW_STORIES_PATH)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}
