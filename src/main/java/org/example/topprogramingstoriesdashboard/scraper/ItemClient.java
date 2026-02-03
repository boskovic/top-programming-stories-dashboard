package org.example.topprogramingstoriesdashboard.scraper;

import org.springframework.web.client.RestClient;

public class ItemClient implements ItemGateway {

    public static final String ITEM_PATH = "/item";
    private final String baseUrl;

    public ItemClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public ItemDto getItem(Long id) {
        return RestClient
                .builder()
                .baseUrl(baseUrl)
                .build()
                .get()
                .uri(ITEM_PATH + "/" + id + ".json")
                .retrieve()
                .body(ItemDto.class);
    }
}
