package org.example.topprogramingstoriesdashboard.backend.web;


import org.example.topprogramingstoriesdashboard.backend.service.GetNewStoriesUseCase;
import org.example.topprogramingstoriesdashboard.backend.service.GetTopStoriesUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/stories")
public class StoriesResource {

    private final GetTopStoriesUseCase getTopStoriesUseCase;
    private final GetNewStoriesUseCase getNewStoriesUseCase;

    public StoriesResource(
            @Autowired GetTopStoriesUseCase getTopStoriesUseCase,
            @Autowired GetNewStoriesUseCase getNewStoriesUseCase
    ) {
        this.getTopStoriesUseCase = getTopStoriesUseCase;
        this.getNewStoriesUseCase = getNewStoriesUseCase;
    }

    @GetMapping("/top")
    ResponseEntity<Set<StoryDto>> topStories() {
        return ResponseEntity.ok().body(getTopStoriesUseCase.getTopStories());
    }

    @GetMapping("/new")
    ResponseEntity<Set<StoryDto>> newStories() {
        return ResponseEntity.ok().body(getNewStoriesUseCase.getNewStories());
    }
}
