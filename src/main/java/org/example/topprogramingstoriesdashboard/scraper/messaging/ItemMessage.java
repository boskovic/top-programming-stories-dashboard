package org.example.topprogramingstoriesdashboard.scraper.messaging;

import org.example.topprogramingstoriesdashboard.scraper.Ranking;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ItemMessage(
                Long id1, String by1, Integer descendants1, Set<Long> kids1, Integer score1, Long time1, String title1,
                ItemType type1, String url1, Boolean deleted1, String text1, Boolean dead1, Long parent1, Long poll1,
                Set<Long> parts1, Set<Ranking> rankings1
        ))) return false;
        return Objects.equals(id, id1) && Objects.equals(by, by1) && Objects.equals(time, time1) && Objects.equals(poll, poll1) && Objects.equals(url, url1) && Objects.equals(text, text1) && Objects.equals(parent, parent1) && Objects.equals(title, title1) && Objects.equals(dead, dead1) && Objects.equals(score, score1) && type == type1 && Objects.equals(kids, kids1) && Objects.equals(deleted, deleted1) && Objects.equals(parts, parts1) && Objects.equals(descendants, descendants1) && Objects.equals(rankings, rankings1);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
