package org.example.topprogramingstoriesdashboard.scraper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;
import java.util.Set;

import static org.example.topprogramingstoriesdashboard.scraper.Ranking.NEW;
import static org.example.topprogramingstoriesdashboard.scraper.Ranking.TOP;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ScraperTest {

    @MockitoBean
    private NewStoriesGateway newStoriesGateway;
    @MockitoBean
    private TopStoriesGateway topStoriesGateway;
    @MockitoBean
    private ItemGateway itemGateway;
    @MockitoBean
    private ItemMessageSender itemMessageSender;

    @Autowired
    private Scraper sus;

    @Test
    void scrapperCorrectlyFetchesAndSendsItems() {
        var ID1 = 1L;
        var ID2 = 2L;
        var ID3 = 3L;
        var ID4 = 4L;
        when(newStoriesGateway.getNewStories()).thenReturn(Set.of(ID1,ID2));
        when(topStoriesGateway.getTopStories()).thenReturn(Set.of(ID1,ID3,ID4));
        when(itemGateway.getItem(eq(ID1))).thenReturn(itemDtoOptional(ID1));
        when(itemGateway.getItem(eq(ID2))).thenReturn(itemDtoOptional(ID2));
        when(itemGateway.getItem(eq(ID3))).thenReturn(itemDtoOptional(ID3));
        when(itemGateway.getItem(eq(ID4))).thenReturn(Optional.empty());

        sus.scrapItems();

        verify(itemMessageSender, times(3)).send(any());
        verify(itemMessageSender).send(eq(itemMessage(ID1, Set.of(NEW, TOP))));
        verify(itemMessageSender).send(itemMessage(ID2, Set.of(NEW)));
        verify(itemMessageSender).send(itemMessage(ID3, Set.of(TOP)));
    }

    private Optional<ItemDto> itemDtoOptional(Long id) {
        return Optional.of(
                ItemDto.builder()
                .id(id)
                .build()
        );
    }

    private ItemMessage itemMessage(Long id, Set<Ranking> rankings) {
        return ItemMessage.builder()
                .id(id)
                .rankings(rankings)
                .build();
    }
}
