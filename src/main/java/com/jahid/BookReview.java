package com.jahid;

import java.util.Objects;

public class BookReview {

    private String title;
    private String review;

    public BookReview() {
    }

    public BookReview(String title, String review) {
        this.title = title;
        this.review = review;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookReview that = (BookReview) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(review, that.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, review);
    }

    @Override
    public String toString() {
        return "BookReview{" +
                "title='" + title + '\'' +
                ", review='" + review + '\'' +
                '}';
    }
}
