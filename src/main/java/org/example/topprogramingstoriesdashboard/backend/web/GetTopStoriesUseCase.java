package org.example.topprogramingstoriesdashboard.backend.web;

import java.util.Set;

public interface GetTopStoriesUseCase {

    Set<StoryDto> getTopStories();
}
