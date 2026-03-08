package org.example.topprogramingstoriesdashboard.backend.service;

import org.example.topprogramingstoriesdashboard.backend.web.StoryDto;

import java.util.Set;
import java.util.stream.Collectors;

public class NewStories implements GetNewStoriesUseCase{

    private final ItemMessageRepository itemMessageRepository;
    private final ItemMessageToStoryDtoMapper itemMessageToStoryDtoMapper;

    public NewStories(
            ItemMessageRepository itemMessageRepository,
            ItemMessageToStoryDtoMapper itemMessageToStoryDtoMapper
    ) {
        this.itemMessageRepository = itemMessageRepository;
        this.itemMessageToStoryDtoMapper = itemMessageToStoryDtoMapper;
    }

    @Override
    public Set<StoryDto> getNewStories() {
        return itemMessageRepository.findNewItems()
            .stream()
            .map(itemMessageToStoryDtoMapper::map)
            .collect(Collectors.toSet());
    }
}
