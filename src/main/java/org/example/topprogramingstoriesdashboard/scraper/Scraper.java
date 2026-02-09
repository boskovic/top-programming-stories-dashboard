package org.example.topprogramingstoriesdashboard.scraper;

import static java.util.stream.Collectors.toSet;

public class Scraper {

    private final ItemIdFetcher itemIdFetcher;
    private final ItemsFetcher itemsFetcher;
    private final ItemMessageSender itemMessageSender;
    private final ItemDtoToItemMessageMapper itemDtoToItemMessageMapper;

    public Scraper(
            ItemIdFetcher itemIdFetcher,
            ItemsFetcher itemsFetcher,
            ItemMessageSender itemMessageSender,
            ItemDtoToItemMessageMapper itemDtoToItemMessageMapper
        ) {
        this.itemIdFetcher = itemIdFetcher;
        this.itemsFetcher = itemsFetcher;
        this.itemMessageSender = itemMessageSender;
        this.itemDtoToItemMessageMapper = itemDtoToItemMessageMapper;
    }

    public void scrapItems() {
        var itemIdWithRankings = itemIdFetcher.fetchItemIds();
        var itemDtos = itemsFetcher.fetchItems(itemIdWithRankings.keySet());
        var itemMessages = itemDtos.stream()
                .map(itemDto -> itemDtoToItemMessageMapper.map(itemDto, itemIdWithRankings))
                .collect(toSet());

        for (ItemMessage itemMessage : itemMessages){
            itemMessageSender.send(itemMessage);
        }
    }
}
