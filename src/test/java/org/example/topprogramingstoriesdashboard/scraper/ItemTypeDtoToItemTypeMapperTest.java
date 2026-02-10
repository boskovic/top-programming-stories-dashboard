package org.example.topprogramingstoriesdashboard.scraper;

import org.example.topprogramingstoriesdashboard.scraper.messaging.ItemType;
import org.example.topprogramingstoriesdashboard.scraper.web.ItemTypeDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class ItemTypeDtoToItemTypeMapperTest {

    @Test
    public void mapItemTypeDtoToItemType() {
        var mapper = new ItemTypeDtoToItemTypeMapper();

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(mapper.map(ItemTypeDto.JOB)).isEqualTo(ItemType.JOB);
            softAssertions.assertThat(mapper.map(ItemTypeDto.POLL)).isEqualTo(ItemType.POLL);
            softAssertions.assertThat(mapper.map(ItemTypeDto.COMMENT)).isEqualTo(ItemType.COMMENT);
            softAssertions.assertThat(mapper.map(ItemTypeDto.POLLOPT)).isEqualTo(ItemType.POLLOPT);
            softAssertions.assertThat(mapper.map(ItemTypeDto.STORY)).isEqualTo(ItemType.STORY);
        });
    }
}
