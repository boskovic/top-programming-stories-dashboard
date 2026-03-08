package org.example.topprogramingstoriesdashboard.backend.service;

import org.example.topprogramingstoriesdashboard.scraper.Ranking;
import org.example.topprogramingstoriesdashboard.scraper.messaging.ItemMessage;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.example.topprogramingstoriesdashboard.scraper.Ranking.NEW;
import static org.example.topprogramingstoriesdashboard.scraper.Ranking.TOP;
import static org.junit.jupiter.api.Assertions.*;

class ItemMessageRepositoryTest {

    @Test
    public void findSavedInRepository() {
        var ID1 = 1L;
        var itemMessage = ItemMessage.builder().id(ID1).build();
        var sus = new ItemMessageRepository();
        sus.save(itemMessage);

        var result = sus.find(ID1);

        assertSoftly(softAssertions -> {
                softAssertions.assertThat(result.isPresent()).isTrue();
                softAssertions.assertThat(itemMessage).isEqualTo(result.get());
            }
        );
    }

    @Test
    public void dontFindAnythingIfNotSaved() {
        var ID = 1L;
        var sus = new ItemMessageRepository();

        var result = sus.find(ID);

        assertFalse(result.isPresent());
    }

    @Test
    public void findAllNewItems(){
        var itemMessage1 = itemMessage(1L, Set.of(NEW));
        var itemMessage2 = itemMessage(2L, Set.of(NEW, TOP));
        var itemMessage3 = itemMessage(3L, Set.of(TOP));
        var sus = new ItemMessageRepository();
        sus.save(itemMessage1);
        sus.save(itemMessage2);
        sus.save(itemMessage3);

        var result = sus.findNewItems();

        assertEquals(Set.of(itemMessage1, itemMessage2), result);
    }

    @Test
    public void findAllTopItems(){
        var itemMessage1 = itemMessage(1L, Set.of(NEW));
        var itemMessage2 = itemMessage(2L, Set.of(NEW, TOP));
        var itemMessage3 = itemMessage(3L, Set.of(TOP));
        var sus = new ItemMessageRepository();
        sus.save(itemMessage1);
        sus.save(itemMessage2);
        sus.save(itemMessage3);

        var result = sus.findTopItems();

        assertEquals(Set.of(itemMessage2, itemMessage3), result);
    }

    private ItemMessage itemMessage(Long id, Set<Ranking> rankings) {
        return ItemMessage.builder()
                .id(id)
                .rankings(rankings)
                .build();
    }
}