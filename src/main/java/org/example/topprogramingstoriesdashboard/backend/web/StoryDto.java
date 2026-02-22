package org.example.topprogramingstoriesdashboard.backend.web;

import org.example.topprogramingstoriesdashboard.scraper.web.ItemTypeDto;

import java.util.Set;

public record StoryDto(
    Long id,
    String by,
    Integer descendants,
    Set<Long> kids,
    Integer score,
    Long time,
    String title,
    ItemTypeDto type,
    String url,
    Boolean deleted,
    String text,
    Boolean dead,
    Long parent,
    Long poll,
    Set<Long> parts
) {
}
