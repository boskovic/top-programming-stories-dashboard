package org.example.topprogramingstoriesdashboard.scraper;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.util.stream.Collectors.toSet;

public class ItemsFetcher {

    private final ItemGateway itemGateway;

    public ItemsFetcher(ItemGateway itemGateway) {
        this.itemGateway = itemGateway;
    }

    public Set<ItemDto> fetchItems(Set<Long> itemIds) {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var itemFutures = itemIds.stream()
                .map(itemId -> executor.submit(
                        () -> itemGateway.getItem(itemId))
                )
                .collect(toSet());
            return itemFutures.stream()
                    .map(this::fetchSilently)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(toSet());
        }
    }

    private Optional<ItemDto> fetchSilently(Future<Optional<ItemDto>> futureOfOptionalItemDto) {
        try {
            return futureOfOptionalItemDto.get();
        } catch (InterruptedException| ExecutionException e) {
            return Optional.empty();
        }
    }
}
