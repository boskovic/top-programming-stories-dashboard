package org.example.topprogramingstoriesdashboard.backend.service;

import org.example.topprogramingstoriesdashboard.backend.web.StoryTypeDto;
import org.example.topprogramingstoriesdashboard.scraper.messaging.ItemType;

public class ItemTypeToStoryTypeDtoMapper {

    public StoryTypeDto map(ItemType itemTypeDto) {
        return switch (itemTypeDto){
            case JOB -> StoryTypeDto.JOB;
            case POLL -> StoryTypeDto.POLL;
            case COMMENT -> StoryTypeDto.COMMENT;
            case STORY -> StoryTypeDto.STORY;
            case POLLOPT -> StoryTypeDto.POLLOPT;
            case null -> null;
        };
    }
}
