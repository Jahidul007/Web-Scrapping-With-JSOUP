package com.jahid;

import java.util.Objects;

public class Link {
    int id;
    String link;

    public Link(int id, String link) {
        this.id = id;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link1 = (Link) o;
        return id == link1.id &&
                Objects.equals(link, link1.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, link);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
