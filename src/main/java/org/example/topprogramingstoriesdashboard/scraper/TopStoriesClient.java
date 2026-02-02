package org.example.topprogramingstoriesdashboard.scraper;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

public class TopStoriesClient implements TopStoriesGateway {

    public static final String TOP_STORIES_PATH = "/topstories.json";

    private final String baseUrl;

    public TopStoriesClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public List<Integer> getTopStories() {
        return RestClient
                .builder()
                .baseUrl(baseUrl)
                .build()
                .get()
                .uri(TOP_STORIES_PATH)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}
