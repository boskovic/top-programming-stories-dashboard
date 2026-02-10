package org.example.topprogramingstoriesdashboard.scraper.web;

import java.util.Optional;

public interface ItemGateway {

    Optional<ItemDto> getItem(Long id);
}
