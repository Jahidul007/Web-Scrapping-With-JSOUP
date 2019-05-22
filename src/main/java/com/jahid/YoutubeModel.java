package com.jahid;

import java.util.ArrayList;


public class YoutubeModel {

    String link;
    ArrayList<String> comments;


   /* public YoutubeModel(int id, String title, String link, String noOfViews, ArrayList<String> comments) {
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
                Objects.equals(title, that.title) &&
                Objects.equals(link, that.link) &&
                Objects.equals(noOfViews, that.noOfViews) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, link, noOfViews, comments);
    }*/



    public YoutubeModel(String link, ArrayList<String> comments) {
        this.link = link;
        this.comments = comments;
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

}