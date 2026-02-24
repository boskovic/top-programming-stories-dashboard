package org.example.topprogramingstoriesdashboard.backend.service;

import org.example.topprogramingstoriesdashboard.backend.web.StoryDto;

import java.util.Set;

public interface GetNewStoriesUseCase {
    Set<StoryDto> getNewStories();
}
