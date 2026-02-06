package org.example.topprogramingstoriesdashboard.scraper;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class ItemDtoToItemMessageMapperTest {

    private final Long ID = 1L;
    private final String BY = "BY";
    private final Integer DESCENDANTS = 1;
    private final Set<Long> KIDS = Set.of(2L,3L);
    private final Integer SCORE = 1;
    private final Long TIME = 1L;
    private final String TITLE = "TITLE";
    private final ItemTypeDto TYPE = ItemTypeDto.COMMENT;
    private final String URL = "URL";
    private final Boolean DELETED = false;
    private final String TEXT = "TEXT";
    private final Boolean DEAD = false;
    private final Long PARENT = 100L;
    private final Long POLL = 101L;
    private final Set<Long> PARTS = Set.of(200L,201L);


    @Test
    public void mapItemDtoAndStoryRankingsToItemMessage() {
        var rankings = Set.of(Ranking.values());
        var allRankings = Map.of(1L, rankings);
        var itemDto = new ItemDto(
                ID, BY, DESCENDANTS, KIDS, SCORE, TIME, TITLE, TYPE, URL, DELETED, TEXT, DEAD, PARENT, POLL, PARTS
        );
        var mapper = new ItemDtoToItemMessageMapper();

        var result = mapper.map(itemDto, allRankings);

        assertSoftly(softAssertions -> {
                    softAssertions.assertThat(result.id()).isEqualTo(ID);
                    softAssertions.assertThat(result.by()).isEqualTo(BY);
                    softAssertions.assertThat(result.descendants()).isEqualTo(DESCENDANTS);
                    softAssertions.assertThat(result.kids()).isEqualTo(KIDS);
                    softAssertions.assertThat(result.score()).isEqualTo(SCORE);
                    softAssertions.assertThat(result.time()).isEqualTo(TIME);
                    softAssertions.assertThat(result.title()).isEqualTo(TITLE);
                    softAssertions.assertThat(result.type()).isEqualTo(ItemType.COMMENT);
                    softAssertions.assertThat(result.url()).isEqualTo(URL);
                    softAssertions.assertThat(result.deleted()).isEqualTo(DELETED);
                    softAssertions.assertThat(result.text()).isEqualTo(TEXT);
                    softAssertions.assertThat(result.dead()).isEqualTo(DEAD);
                    softAssertions.assertThat(result.parent()).isEqualTo(PARENT);
                    softAssertions.assertThat(result.poll()).isEqualTo(POLL);
                    softAssertions.assertThat(result.parts()).isEqualTo(PARTS);
                    softAssertions.assertThat(result.rankings()).isEqualTo(rankings);
                });
    }
}
