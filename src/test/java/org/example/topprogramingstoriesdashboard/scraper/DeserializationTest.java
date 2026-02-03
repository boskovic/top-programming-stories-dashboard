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

@WireMockTest(httpPort = DeserializationTest.HTTP_PORT)
public class DeserializationTest {

    static final int HTTP_PORT = 8888;
    private static final String LOCALHOST = "http://localhost";
    private static final String BASE_URL = LOCALHOST + ":" + HTTP_PORT;

    @Test
    public void testTopStoriesDeserialization() throws Exception {
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
    public void testNewStoriesDeserialization() throws IOException {
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
    public void testItemDeserialization() throws IOException{
        var itemId = 12345L;
        stubFor(get("/item/" + itemId + ".json")
                .willReturn(
                    ok()
                    .withBody(readJson(itemId + ".json"))
                    .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                ));
        var item = new ItemClient(BASE_URL);

        var result = item.getItem(itemId);

        assertThat(result.id()).isEqualTo(itemId);
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
