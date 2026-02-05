package org.example.topprogramingstoriesdashboard.scraper;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import joptsimple.internal.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.util.Objects.isNull;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WireMockTest(httpPort = DeserializationTest.HTTP_PORT)
public class DeserializationTest {

    static final int HTTP_PORT = 8888;
    private static final String LOCALHOST = "http://localhost";
    private static final String BASE_URL = LOCALHOST + ":" + HTTP_PORT;

    @Test
    public void topStoriesDeserializedCorrectly() throws Exception {
        stubFor(get(TopStoriesClient.TOP_STORIES_PATH)
            .willReturn(
                ok()
                .withBody(readJson("topstories.json"))
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            ));
        var topStories = new TopStoriesClient(BASE_URL);

        var result = topStories.getTopStories();

        assertThat(result.size() == 500);
    }

    @Test
    public void topStoriesAreDeserializedToEmptySetWhenError(){
        stubFor(get(TopStoriesClient.TOP_STORIES_PATH)
                .willReturn(notFound())
        );
        var topStories = new TopStoriesClient(BASE_URL);

        var result = topStories.getTopStories();

        assertTrue(result.isEmpty());
    }

    @Test
    public void newStoriesAreDeserializedCorrectly() throws IOException {
        stubFor(get(NewStoriesClient.NEW_STORIES_PATH)
            .willReturn(
                ok()
                .withBody(readJson("newstories.json"))
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            ));
        var newStories = new NewStoriesClient(BASE_URL);

        var result = newStories.getNewStories();

        assertThat(result.size() == 500);
    }

    @Test
    public void newStoriesAreDeserializedToEmptySetWhenError(){
        stubFor(get(TopStoriesClient.TOP_STORIES_PATH)
                .willReturn(notFound())
        );
        var newStories = new NewStoriesClient(BASE_URL);

        var result = newStories.getNewStories();

        assertTrue(result.isEmpty());
    }

    @Test
    public void itemsAreDeserializedCorrectly() throws IOException{
        var itemId = 12345L;
        stubFor(get("/item/" + itemId + ".json")
                .willReturn(
                    ok()
                    .withBody(readJson(itemId + ".json"))
                    .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                ));
        var item = new ItemClient(BASE_URL);

        var result = item.getItem(itemId);

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(result.id()).isEqualTo(itemId);
            softAssertions.assertThat(result.type()).isEqualTo(ItemTypeDto.STORY);
        });
    }

    private String readJson(String pathInResources) throws IOException {
        try (var fileStream = getClass().getClassLoader().getResourceAsStream(pathInResources)) {
            if (isNull(fileStream)) {
                return Strings.EMPTY;
            }
            return new String(fileStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
