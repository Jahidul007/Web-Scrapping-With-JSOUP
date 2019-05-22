package com.jahid;

import java.util.ArrayList;
import java.util.Objects;


public class YoutubeModel {

    int id;
    String link;
    ArrayList<String> comments;


    public YoutubeModel(int id, String link, ArrayList<String> comments) {
        this.id = id;
        this.link = link;
        this.comments = comments;
    }

    public int getId() {
        return id;
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

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YoutubeModel that = (YoutubeModel) o;
        return id == that.id &&
                Objects.equals(link, that.link) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, link, comments);
    }
}