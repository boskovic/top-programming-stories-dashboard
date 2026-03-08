package org.example.topprogramingstoriesdashboard.backend.service;

import org.example.topprogramingstoriesdashboard.backend.web.StoryDto;

import java.util.Set;
import java.util.stream.Collectors;

public class TopStories implements GetTopStoriesUseCase{

    private final ItemMessageRepository itemMessageRepository;
    private final ItemMessageToStoryDtoMapper itemMessageToStoryDtoMapper;

    public TopStories(
            ItemMessageRepository itemMessageRepository,
            ItemMessageToStoryDtoMapper itemMessageToStoryDtoMapper
    ) {
        this.itemMessageRepository = itemMessageRepository;
        this.itemMessageToStoryDtoMapper = itemMessageToStoryDtoMapper;
    }

    @Override
    public Set<StoryDto> getTopStories() {
        return itemMessageRepository.findTopItems()
                .stream()
                .map(itemMessageToStoryDtoMapper::map)
                .collect(Collectors.toSet());
    }
}
