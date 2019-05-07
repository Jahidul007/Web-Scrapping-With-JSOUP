package com.jahid;

import java.util.Objects;

public class YouTube {

    int id;
    String title;
    String link;
    String noOfViews;
    String comments;

    public YouTube(int id, String title, String link, String noOfViews, String comments) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.noOfViews = noOfViews;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNoOfViews() {
        return noOfViews;
    }

    public void setNoOfViews(String noOfViews) {
        this.noOfViews = noOfViews;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YouTube youTube = (YouTube) o;
        return Objects.equals(id, youTube.id) &&
                Objects.equals(title, youTube.title) &&
                Objects.equals(link, youTube.link) &&
                Objects.equals(noOfViews, youTube.noOfViews) &&
                Objects.equals(comments, youTube.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, link, noOfViews, comments);
    }
}
