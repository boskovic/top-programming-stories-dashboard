package org.example.topprogramingstoriesdashboard.backend.service;

import org.example.topprogramingstoriesdashboard.backend.web.StoryDto;

import java.util.Set;

public class NewStories implements GetNewStoriesUseCase{

    @Override
    public Set<StoryDto> getNewStories() {
        return Set.of();
    }
}
