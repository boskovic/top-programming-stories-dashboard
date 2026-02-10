package org.example.topprogramingstoriesdashboard.scraper;

import org.example.topprogramingstoriesdashboard.scraper.web.ItemDto;

import java.util.Map;
import java.util.Set;

public class ItemDtoToItemMessageMapper {

    private final ItemTypeDtoToItemTypeMapper itemTypeMapper = new ItemTypeDtoToItemTypeMapper();

    public ItemMessage map(ItemDto itemDto, Map<Long, Set<Ranking>> rankingsMap) {
        return ItemMessage.builder()
                .id(itemDto.id())
                .by(itemDto.by())
                .descendants(itemDto.descendants())
                .kids(itemDto.kids())
                .score(itemDto.score())
                .time(itemDto.time())
                .title(itemDto.title())
                .type(itemTypeMapper.map(itemDto.type()))
                .url(itemDto.url())
                .deleted(itemDto.deleted())
                .text(itemDto.text())
                .dead(itemDto.dead())
                .parent(itemDto.parent())
                .poll(itemDto.poll())
                .parts(itemDto.parts())
                .rankings(rankingsMap.get(itemDto.id()))
                .build();
    }
}
