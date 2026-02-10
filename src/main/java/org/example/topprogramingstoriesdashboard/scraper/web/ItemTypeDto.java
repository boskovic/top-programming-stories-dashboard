package org.example.topprogramingstoriesdashboard.scraper.web;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ItemTypeDto {
    JOB("job"), STORY("story"), COMMENT("comment"), POLL("poll"), POLLOPT("pollopt");

    ItemTypeDto(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @JsonValue
    private final String type;
}
