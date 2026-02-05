package org.example.topprogramingstoriesdashboard.scraper;

import java.util.Set;

public record ItemMessage(
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
    Set<Long> parts,
    Set<Ranking> rankings
) {
}
