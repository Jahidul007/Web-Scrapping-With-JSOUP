package com.jahid;

import java.util.Objects;

public class YouTube {

    String title;
    String link;
    String noOfViews;
    String noOfComments;
    String comments;
    String noOfLikes;
    String noOfDislikes;

    public YouTube() {
    }

    public YouTube(String title, String link, String noOfViews, String noOfComments, String comments) {
        this.title = title;
        this.link = link;
        this.noOfViews = noOfViews;
        this.noOfComments = noOfComments;
        this.comments = comments;
    }

    public YouTube(String title, String link, String noOfViews, String noOfComments, String comments, String noOfLikes, String noOfDislikes) {
        this.title = title;
        this.link = link;
        this.noOfViews = noOfViews;
        this.noOfComments = noOfComments;
        this.comments = comments;
        this.noOfLikes = noOfLikes;
        this.noOfDislikes = noOfDislikes;
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

    public String getNoOfComments() {
        return noOfComments;
    }

    public void setNoOfComments(String noOfComments) {
        this.noOfComments = noOfComments;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getNoOfLikes() {
        return noOfLikes;
    }

    public void setNoOfLikes(String noOfLikes) {
        this.noOfLikes = noOfLikes;
    }

    public String getNoOfDislikes() {
        return noOfDislikes;
    }

    public void setNoOfDislikes(String noOfDislikes) {
        this.noOfDislikes = noOfDislikes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YouTube youTube = (YouTube) o;
        return Objects.equals(title, youTube.title) &&
                Objects.equals(link, youTube.link) &&
                Objects.equals(noOfViews, youTube.noOfViews) &&
                Objects.equals(noOfComments, youTube.noOfComments) &&
                Objects.equals(comments, youTube.comments) &&
                Objects.equals(noOfLikes, youTube.noOfLikes) &&
                Objects.equals(noOfDislikes, youTube.noOfDislikes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, link, noOfViews, noOfComments, comments, noOfLikes, noOfDislikes);
    }

    @Override
    public String toString() {
        return "YouTube{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", noOfViews='" + noOfViews + '\'' +
                ", noOfComments='" + noOfComments + '\'' +
                ", comments='" + comments + '\'' +
                ", noOfLikes='" + noOfLikes + '\'' +
                ", noOfDislikes='" + noOfDislikes + '\'' +
                '}';
    }
}
