package org.example.topprogramingstoriesdashboard.scraper;

import org.example.topprogramingstoriesdashboard.scraper.web.ItemType;

import java.util.Set;

public record ItemMessage(
    Long id,
    String by,
    Integer descendants,
    Set<Long> kids,
    Integer score,
    Long time,
    String title,
    ItemType type,
    String url,
    Boolean deleted,
    String text,
    Boolean dead,
    Long parent,
    Long poll,
    Set<Long> parts,
    Set<Ranking> rankings
) {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String by;
        private Integer descendants;
        private Set<Long> kids;
        private Integer score;
        private Long time;
        private String title;
        private ItemType type;
        private String url;
        private Boolean deleted;
        private String text;
        private Boolean dead;
        private Long parent;
        private Long poll;
        private Set<Long> parts;
        private Set<Ranking> rankings;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder by(String by) {
            this.by = by;
            return this;
        }

        public Builder descendants(Integer descendants) {
            this.descendants = descendants;
            return this;
        }

        public Builder kids(Set<Long> kids) {
            this.kids = kids;
            return this;
        }

        public Builder score(Integer score) {
            this.score = score;
            return this;
        }

        public Builder time(Long time) {
            this.time = time;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder type(ItemType type) {
            this.type = type;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder deleted(Boolean deleted) {
            this.deleted = deleted;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder dead(Boolean dead) {
            this.dead = dead;
            return this;
        }

        public Builder parent(Long parent) {
            this.parent = parent;
            return this;
        }

        public Builder poll(Long poll) {
            this.poll = poll;
            return this;
        }

        public Builder parts(Set<Long> parts) {
            this.parts = parts;
            return this;
        }

        public Builder rankings(Set<Ranking> rankings) {
            this.rankings = rankings;
            return this;
        }

        public ItemMessage build() {
            return new ItemMessage(
                    id, by, descendants, kids, score, time, title, type,
                    url, deleted, text, dead, parent, poll, parts, rankings
            );
        }
    }
}
