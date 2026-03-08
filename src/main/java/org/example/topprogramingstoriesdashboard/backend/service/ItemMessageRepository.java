package org.example.topprogramingstoriesdashboard.backend.service;

import org.example.topprogramingstoriesdashboard.scraper.messaging.ItemMessage;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class ItemMessageRepository {

    private final Set<ItemMessage> items = new HashSet<>();

    public void save(ItemMessage itemMessage) {
        items.add(itemMessage);
    }

    public Optional<ItemMessage> find(Long id){
        return items.stream()
                .filter(itemMessage -> Objects.equals(itemMessage.id(), id))
                .findAny();
    }

}
