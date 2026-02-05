package org.example.topprogramingstoriesdashboard.scraper;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Ranking {
    TOP("top"), NEW("new");

    Ranking(String ranking) {
        this.ranking = ranking;
    }

    public String getRanking() {
        return ranking;
    }

    @JsonValue
    private final String ranking;
}
