package org.example.topprogramingstoriesdashboard.backend.service;

import org.example.topprogramingstoriesdashboard.backend.web.StoryDto;
import org.example.topprogramingstoriesdashboard.scraper.messaging.ItemMessage;

public class ItemMessageToStoryDtoMapper {

    private final ItemTypeToStoryTypeDtoMapper itemTypeToStoryTypeDtoMapper = new ItemTypeToStoryTypeDtoMapper();

    public StoryDto map(ItemMessage itemMessage) {
        return StoryDto.builder()
                .id(itemMessage.id())
                .by(itemMessage.by())
                .descendants(itemMessage.descendants())
                .kids(itemMessage.kids())
                .score(itemMessage.score())
                .time(itemMessage.time())
                .title(itemMessage.title())
                .type(itemTypeToStoryTypeDtoMapper.map(itemMessage.type()))
                .url(itemMessage.url())
                .deleted(itemMessage.deleted())
                .text(itemMessage.text())
                .dead(itemMessage.dead())
                .parent(itemMessage.parent())
                .poll(itemMessage.poll())
                .parts(itemMessage.parts())
                .build();
    }
}
