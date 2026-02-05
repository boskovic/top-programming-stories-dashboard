package org.example.topprogramingstoriesdashboard.scraper;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.Set;

public class NewStoriesClient implements NewStoriesGateway {
    public static final String NEW_STORIES_PATH = "/newstories.json";

    private final String baseUrl;

    public NewStoriesClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public Set<Long> getNewStories() {
        try {
            return RestClient
                    .builder()
                    .baseUrl(baseUrl)
                    .build()
                    .get()
                    .uri(NEW_STORIES_PATH)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
        } catch (Exception e) {
            return Collections.emptySet();
        }
    }
}
