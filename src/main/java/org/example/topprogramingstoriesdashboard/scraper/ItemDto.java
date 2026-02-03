package org.example.topprogramingstoriesdashboard.scraper;

import java.util.List;

public record ItemDto(
        String by,
        Integer descendants,
        Long id,
        List<Integer> kids,
        Integer score,
        Long time,
        String title,
        String type,
        String url
) { }
