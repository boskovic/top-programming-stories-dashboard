package org.example.topprogramingstoriesdashboard.scraper;

import java.util.Optional;

public interface ItemGateway {

    Optional<ItemDto> getItem(Long id);
}
