package org.example.topprogramingstoriesdashboard.scraper;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

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

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void scrapItems() {
        System.out.println("Scraping started");
        var startedAt = System.currentTimeMillis();
        var itemIdWithRankings = itemIdFetcher.fetchItemIds();
        var itemDtos = itemsFetcher.fetchItems(itemIdWithRankings.keySet());
        var itemMessages = itemDtos.stream()
                .map(itemDto -> itemDtoToItemMessageMapper.map(itemDto, itemIdWithRankings))
                .collect(toSet());

        for (ItemMessage itemMessage : itemMessages){
            itemMessageSender.send(itemMessage);
        }

        var endedAt = System.currentTimeMillis();
        System.out.println("Scraping started ended. Scrapping took: " + (endedAt-startedAt) + "ms. Scraped are " + itemDtos.size() + " items");
    }
}
