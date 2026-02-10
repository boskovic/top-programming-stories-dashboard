package org.example.topprogramingstoriesdashboard.scraper;

import org.example.topprogramingstoriesdashboard.scraper.web.NewStoriesGateway;
import org.example.topprogramingstoriesdashboard.scraper.web.TopStoriesGateway;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.example.topprogramingstoriesdashboard.scraper.Ranking.*;

public class ItemIdFetcher {

    private final NewStoriesGateway newStoriesGateway;
    private final TopStoriesGateway topStoriesGateway;

    public ItemIdFetcher(NewStoriesGateway newStoriesGateway, TopStoriesGateway topStoriesGateway) {
        this.newStoriesGateway = newStoriesGateway;
        this.topStoriesGateway = topStoriesGateway;
    }

    public Map<Long, Set<Ranking>> fetchItemIds() {

        var newStories = newStoriesGateway.getNewStories();
        var topStories = topStoriesGateway.getTopStories();

        var allIds = Stream.concat(newStories.stream(), topStories.stream()).collect(Collectors.toSet());

        return allIds.stream()
                .collect(Collectors.toMap(
                        id -> id,
                        id -> getRankingsForItem(topStories, newStories, id)
                ));

    }

    private Set<Ranking> getRankingsForItem(Set<Long> topStories, Set<Long> newStories, long itemId) {
        if (topStories.contains(itemId) && newStories.contains(itemId)) {
            return Set.of(NEW, TOP);
        } else if (topStories.contains(itemId)) {
            return Set.of(TOP);
        } else {
            return Set.of(NEW);
        }
    }
}
