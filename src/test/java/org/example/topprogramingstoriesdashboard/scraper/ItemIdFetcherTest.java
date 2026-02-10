package org.example.topprogramingstoriesdashboard.scraper;

import com.google.common.collect.Sets;
import org.example.topprogramingstoriesdashboard.scraper.web.NewStoriesGateway;
import org.example.topprogramingstoriesdashboard.scraper.web.TopStoriesGateway;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemIdFetcherTest {

    @Test
    public void itemIdFetcherFetchesTheItemIdsFromDifferentRankings() {
        var newStoriesGatewayMock = mock(NewStoriesGateway.class);
        var newStories = Sets.newHashSet(12345L, 12346L);
        when(newStoriesGatewayMock.getNewStories()).thenReturn(newStories);
        var topStoriesGatewayMock = mock(TopStoriesGateway.class);
        var topStories = Sets.newHashSet(12345L, 12347L);
        when(topStoriesGatewayMock.getTopStories()).thenReturn(topStories);
        var expectedResult = Map.of(
        12345L, Sets.newHashSet(Ranking.TOP, Ranking.NEW),
        12346L, Sets.newHashSet(Ranking.NEW),
        12347L, Sets.newHashSet(Ranking.TOP)
        );
        var sus = new ItemIdFetcher(newStoriesGatewayMock, topStoriesGatewayMock);

        var result = sus.fetchItemIds();

        assertThat(expectedResult).isEqualTo(result);
    }
}
