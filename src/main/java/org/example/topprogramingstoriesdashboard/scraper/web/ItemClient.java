package org.example.topprogramingstoriesdashboard.scraper.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestClient;

import java.util.Optional;

public class ItemClient implements ItemGateway {

    public static final String ITEM_PATH = "/item";
    private static final Logger logger = LogManager.getLogger(ItemClient.class);

    private final String baseUrl;

    public ItemClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public Optional<ItemDto> getItem(Long id) {
        try {
            return Optional.ofNullable(RestClient
                    .builder()
                    .baseUrl(baseUrl)
                    .build()
                    .get()
                    .uri(ITEM_PATH + "/" + id + ".json")
                    .retrieve()
                    .body(ItemDto.class)
            );
        } catch (Exception e) {
            logger.warn("Fetching of item with the id {} failed", id, e);
            return Optional.empty();
        }
    }
}
