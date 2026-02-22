package org.example.topprogramingstoriesdashboard.backend.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/stories")
public class StoriesResource {

    @GetMapping("/top")
    ResponseEntity<Set<StoryDto>> topStories() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/new")
    ResponseEntity<Set<StoryDto>> newStories() {
        return ResponseEntity.ok().build();
    }
}
