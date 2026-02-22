package org.example.topprogramingstoriesdashboard.backend.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stories")
public class StoriesResource {

    @GetMapping("/top")
    ResponseEntity<List<StoryDto>> topStories() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/new")
    ResponseEntity<List<StoryDto>> newStories() {
        return ResponseEntity.ok().build();
    }
}
