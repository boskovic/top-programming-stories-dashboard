package org.example.topprogramingstoriesdashboard.scraper;

import org.example.topprogramingstoriesdashboard.scraper.web.ItemDto;
import org.example.topprogramingstoriesdashboard.scraper.web.ItemGateway;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemsFetcherTest{

    @Test
    public void fetchAllItemsForTheSubmittedSet() {
        var itemGatewayMock = mock(ItemGateway.class);
        var ID_1 = 1L;
        var itemDto1 = ItemDto.builder().id(ID_1).build();
        when(itemGatewayMock.getItem(eq(ID_1))).thenReturn(Optional.of(itemDto1));
        var ID_2 = 2L;
        var itemDto2 = ItemDto.builder().id(ID_2).build();
        when(itemGatewayMock.getItem(eq(ID_2))).thenReturn(Optional.of(itemDto2));
        var ID_3 = 3L;
        when(itemGatewayMock.getItem(eq(ID_3))).thenReturn(Optional.empty());
        var sus = new ItemsFetcher(itemGatewayMock);
        var ITEM_IDS = Set.of(ID_1, ID_2, ID_3);

        var result = sus.fetchItems(ITEM_IDS);

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(result.size()).isEqualTo(2);
            softAssertions.assertThat(result).contains(itemDto1, itemDto2);
        });
    }
}
