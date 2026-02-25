package org.example.topprogramingstoriesdashboard.backend.service;

import org.example.topprogramingstoriesdashboard.backend.web.StoryTypeDto;
import org.example.topprogramingstoriesdashboard.scraper.messaging.ItemType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class ItemTypeToStoryTypeDtoMapperTest {

    @Test
    public void mapItemTypeToStoryTypeDto() {
        var mapper = new ItemTypeToStoryTypeDtoMapper();

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(mapper.map(ItemType.JOB)).isEqualTo(StoryTypeDto.JOB);
            softAssertions.assertThat(mapper.map(ItemType.POLL)).isEqualTo(StoryTypeDto.POLL);
            softAssertions.assertThat(mapper.map(ItemType.COMMENT)).isEqualTo(StoryTypeDto.COMMENT);
            softAssertions.assertThat(mapper.map(ItemType.POLLOPT)).isEqualTo(StoryTypeDto.POLLOPT);
            softAssertions.assertThat(mapper.map(ItemType.STORY)).isEqualTo(StoryTypeDto.STORY);
        });
    }
}
