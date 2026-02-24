package org.example.topprogramingstoriesdashboard.backend.service;

import org.example.topprogramingstoriesdashboard.backend.web.StoryDto;

import java.util.Set;

public class TopStories implements GetTopStoriesUseCase{

    @Override
    public Set<StoryDto> getTopStories() {
        return Set.of();
    }
}
