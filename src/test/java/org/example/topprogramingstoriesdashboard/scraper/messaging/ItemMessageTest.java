package org.example.topprogramingstoriesdashboard.scraper.messaging;

import org.example.topprogramingstoriesdashboard.scraper.Ranking;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ItemMessageTest {

    @Test
    public void equalsDeliversCorrectResult() {
        var id = 1L;
        var by = "by";
        var descendants = 10;
        var kid1 = 10L;
        var kid2 = 20L;
        var score = 10;
        var time = 100L;
        var title = "title";
        var type = ItemType.JOB;
        var url = "url";
        var deleted = false;
        var text = "text";
        var dead = true;
        var parent = 1000L;
        var poll = 10000L;
        var part1 = 30L;
        var part2 = 40L;
        var ranking1 = Ranking.NEW;
        var ranking2 = Ranking.TOP;
        var item1 = ItemMessage.builder()
                .id(id)
                .by(by)
                .descendants(descendants)
                .kids(Set.of(kid1, kid2))
                .score(score)
                .time(time)
                .title(title)
                .type(type)
                .url(url)
                .deleted(deleted)
                .text(text)
                .dead(dead)
                .parent(parent)
                .poll(poll)
                .parts(Set.of(part1, part2))
                .rankings(Set.of(ranking1, ranking2))
                .build();
        var item2 = ItemMessage.builder()
                .id(id)
                .by(by)
                .descendants(descendants)
                .kids(Set.of(kid2, kid1))
                .score(score)
                .time(time)
                .title(title)
                .type(type)
                .url(url)
                .deleted(deleted)
                .text(text)
                .dead(dead)
                .parent(parent)
                .poll(poll)
                .parts(Set.of(part2, part1))
                .rankings(Set.of(ranking2, ranking1))
                .build();

        assertEquals(item1, item2);
    }
}