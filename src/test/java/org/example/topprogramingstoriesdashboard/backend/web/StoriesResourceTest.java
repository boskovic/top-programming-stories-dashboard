package org.example.topprogramingstoriesdashboard.backend.web;

import org.example.topprogramingstoriesdashboard.backend.configuration.ServicesConfiguration;
import org.example.topprogramingstoriesdashboard.backend.service.ItemMessageRepository;
import org.example.topprogramingstoriesdashboard.scraper.Ranking;
import org.example.topprogramingstoriesdashboard.scraper.messaging.ItemMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.example.topprogramingstoriesdashboard.scraper.Ranking.NEW;
import static org.example.topprogramingstoriesdashboard.scraper.Ranking.TOP;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StoriesResource.class)
@ContextConfiguration(classes = {ServicesConfiguration.class, StoriesResource.class})
class StoriesResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemMessageRepository itemMessageRepository;

    @BeforeEach
    public void initTest(){
        var itemMessageId1 = 1L;
        var itemMessage1 = itemMessage(itemMessageId1, Set.of(NEW));
        itemMessageRepository.save(itemMessage1);
        var itemMessageId2 = 2L;
        var itemMessage2 = itemMessage(itemMessageId2, Set.of(NEW));
        itemMessageRepository.save(itemMessage2);
        var itemMessageId3 = 3L;
        var itemMessage3 = itemMessage(itemMessageId3, Set.of(TOP));
        itemMessageRepository.save(itemMessage3);
        var itemMessageId4 = 4L;
        var itemMessage4 = itemMessage(itemMessageId4, Set.of(TOP, NEW));
        itemMessageRepository.save(itemMessage4);
    }

    @Test
    void returnsOkMessageForTopStoriesWithBody() throws Exception {

        mockMvc.perform(get("/stories/top"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void returnsOkMessageForNewStoriesWithBody() throws Exception {

        mockMvc.perform(get("/stories/new"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }

    private ItemMessage itemMessage(Long id, Set<Ranking> rankings) {
        return ItemMessage.builder()
                .id(id)
                .rankings(rankings)
                .build();
    }
}