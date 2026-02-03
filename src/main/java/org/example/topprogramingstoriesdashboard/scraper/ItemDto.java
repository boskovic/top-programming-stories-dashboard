package org.example.topprogramingstoriesdashboard.scraper;

import java.util.List;

public record ItemDto(
        Long id,
        String by,
        Integer descendants,
        List<Integer> kids,
        Integer score,
        Long time,
        String title,
        ItemTypeDto type,
        String url
) { }
