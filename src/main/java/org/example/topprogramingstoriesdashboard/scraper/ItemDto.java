package org.example.topprogramingstoriesdashboard.scraper;

import java.util.List;

public record ItemDto(
    Long id,
    String by,
    Integer descendants,
    List<Long> kids,
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
    List<Long> parts
) { }
