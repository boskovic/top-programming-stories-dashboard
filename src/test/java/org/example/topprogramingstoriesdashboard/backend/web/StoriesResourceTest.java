package org.example.topprogramingstoriesdashboard.backend.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StoriesResource.class)
class StoriesResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnsOkMessageForTopStories() throws Exception {
        mockMvc.perform(get("/stories/top"))
                .andExpect(status().isOk());
    }

    @Test
    void returnsOkMessageForNewStories() throws Exception {
        mockMvc.perform(get("/stories/new"))
                .andExpect(status().isOk());
    }

}