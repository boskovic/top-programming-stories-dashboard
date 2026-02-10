package org.example.topprogramingstoriesdashboard.scraper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.topprogramingstoriesdashboard.scraper.messaging.ItemMessage;
import org.example.topprogramingstoriesdashboard.scraper.messaging.ItemMessageSender;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toSet;

public class Scraper {

    private static final Logger logger = LogManager.getLogger(Scraper.class);

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
        logger.info("Scraping started");
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
        logger.info("Scraping ended. It took: {}ms. Overall {} items are scraped.", (endedAt-startedAt), itemDtos.size());
    }
}
