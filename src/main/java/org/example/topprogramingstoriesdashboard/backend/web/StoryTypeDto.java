package org.example.topprogramingstoriesdashboard.backend.web;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StoryTypeDto {
    JOB("job"), STORY("story"), COMMENT("comment"), POLL("poll"), POLLOPT("pollopt");

    StoryTypeDto(String type) {
            this.type = type;
        }

    public String getType() {
            return type;
        }

    @JsonValue
    private final String type;
}
