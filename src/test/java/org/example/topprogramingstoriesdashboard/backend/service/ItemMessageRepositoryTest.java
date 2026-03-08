package org.example.topprogramingstoriesdashboard.backend.service;

import org.example.topprogramingstoriesdashboard.scraper.messaging.ItemMessage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
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
}