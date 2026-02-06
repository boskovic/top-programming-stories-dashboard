package org.example.topprogramingstoriesdashboard.scraper;

public class ItemDtoToItemMessageItemTypeDtoToItemTypeMapper {


    public ItemType map(ItemTypeDto itemTypeDto) {
        return switch (itemTypeDto){
            case JOB -> ItemType.JOB;
            case POLL -> ItemType.POLL;
            case COMMENT -> ItemType.COMMENT;
            case STORY -> ItemType.STORY;
            case POLLOPT -> ItemType.POLLOPT;
            case null -> null;
        };
    }
}
