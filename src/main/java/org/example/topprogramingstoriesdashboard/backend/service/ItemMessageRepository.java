package org.example.topprogramingstoriesdashboard.backend.service;

import org.example.topprogramingstoriesdashboard.scraper.messaging.ItemMessage;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.example.topprogramingstoriesdashboard.scraper.Ranking.NEW;

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

    public Set<ItemMessage> findNewItems() {
        return items.stream()
                .filter(itemMessage -> itemMessage.rankings().contains(NEW))
                .collect(Collectors.toSet());
    }
}
