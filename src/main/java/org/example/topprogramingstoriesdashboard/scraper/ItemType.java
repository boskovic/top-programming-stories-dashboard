package org.example.topprogramingstoriesdashboard.scraper;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ItemType {
    JOB("job"), STORY("story"), COMMENT("comment"), POLL("poll"), POLLOPT("pollopt");

    ItemType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @JsonValue
    private final String type;
}
