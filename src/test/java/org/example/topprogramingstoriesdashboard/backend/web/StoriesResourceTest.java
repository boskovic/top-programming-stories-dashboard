package org.example.topprogramingstoriesdashboard.backend.web;

import org.assertj.core.util.Sets;
import org.example.topprogramingstoriesdashboard.backend.service.GetNewStoriesUseCase;
import org.example.topprogramingstoriesdashboard.backend.service.GetTopStoriesUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StoriesResource.class)
class StoriesResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GetTopStoriesUseCase getTopStoriesUseCase;

    @MockitoBean
    private GetNewStoriesUseCase getNewStoriesUseCase;

    @Test
    void returnsOkMessageForTopStoriesWithBody() throws Exception {
        var STORY_ID_1 = 1L;
        var STORY_ID_2 = 2L;
        var story1 = story(STORY_ID_1);
        var story2 = story(STORY_ID_2);
        when(getTopStoriesUseCase.getTopStories()).thenReturn(Set.of(story1, story2));

        mockMvc.perform(get("/stories/top"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void returnsOkMessageForNewStories() throws Exception {
        var STORY_ID_3 = 3L;
        var STORY_ID_4 = 4L;
        var STORY_ID_5 = 5L;
        var story3 = story(STORY_ID_3);
        var story4 = story(STORY_ID_4);
        var story5 = story(STORY_ID_5);
        when(getNewStoriesUseCase.getNewStories()).thenReturn(Set.of(story3, story4, story5));

        mockMvc.perform(get("/stories/new"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }

    private StoryDto story(Long id){
        return new StoryDto(
                id,
                null,
                null,
                Sets.newHashSet(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                Sets.newHashSet()
        );
    }
}