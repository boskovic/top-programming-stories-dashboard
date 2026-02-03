package org.example.topprogramingstoriesdashboard;

import jakarta.websocket.server.PathParam;
import org.example.topprogramingstoriesdashboard.scraper.ItemClient;
import org.example.topprogramingstoriesdashboard.scraper.ItemDto;
import org.example.topprogramingstoriesdashboard.scraper.NewStoriesClient;
import org.example.topprogramingstoriesdashboard.scraper.TopStoriesClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class EndToEndTestController {

    @Value("${application.hackernews.baseurl}")
    private String hackerNewsBaseUrl;

    @GetMapping("/topstories")
    public ResponseEntity<List<Long>> getTopStories() {
        var topStories = new TopStoriesClient(hackerNewsBaseUrl);
        return ResponseEntity.ok(topStories.getTopStories());
    }

    @GetMapping("/newstories")
    public ResponseEntity<List<Long>> getNewStories() {
        var newStories = new NewStoriesClient(hackerNewsBaseUrl);
        return ResponseEntity.ok(newStories.getNewStories());
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<ItemDto> getNewStories(@PathVariable Long id) {
        var item = new ItemClient(hackerNewsBaseUrl);
        return ResponseEntity.ok(item.getItem(id));
    }
}
