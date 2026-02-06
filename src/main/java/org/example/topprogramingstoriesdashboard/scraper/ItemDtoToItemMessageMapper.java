package org.example.topprogramingstoriesdashboard.scraper;

import java.util.Map;
import java.util.Set;

public class ItemDtoToItemMessageMapper {

    private final ItemTypeDtoToItemTypeMapper itemTypeMapper = new ItemTypeDtoToItemTypeMapper();

    public ItemMessage map(ItemDto itemDto, Map<Long, Set<Ranking>> rankingsMap) {
        return new ItemMessage(
                itemDto.id(),
                itemDto.by(),
                itemDto.descendants(),
                itemDto.kids(),
                itemDto.score(),
                itemDto.time(),
                itemDto.title(),
                itemTypeMapper.map(itemDto.type()),
                itemDto.url(),
                itemDto.deleted(),
                itemDto.text(),
                itemDto.dead(),
                itemDto.parent(),
                itemDto.poll(),
                itemDto.parts(),
                rankingsMap.get(itemDto.id())
        );
    }
}
