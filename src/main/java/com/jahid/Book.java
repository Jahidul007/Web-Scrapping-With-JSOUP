package com.jahid;

import java.util.Objects;

public class Book {

    private  String title;
    private  String author;
    private  String image;
    private  String boibazar_price;
    private  String boibazar_url;
    private  String boibazar_rating;
    private  String boibazar_noOfRated;
    private  String rokomari_price;
    private  String rokomari_rating;
    private  String rokomari_url;
    private  String rokomari_noOfrated ;
    private String comments;
    public Book(String title, String comments){
        this.title = title;
        this.comments = comments;
    }

    public Book(String title, String author, String price, String image, String url, String boibazar_rating, String boibazar_noOfRated, String rokomari_price, String rokomari_rating, String rokomari_url, String rokomari_noOfrated) {
        this.title = title;
        this.author = author;
        this.boibazar_price = price;
        this.image = image;
        this.boibazar_url = url;
        this.boibazar_rating = boibazar_rating;
        this.boibazar_noOfRated = boibazar_noOfRated;
        this.rokomari_price = rokomari_price;
        this.rokomari_rating = rokomari_rating;
        this.rokomari_url = rokomari_url;
        this.rokomari_noOfrated = rokomari_noOfrated;
    }

    public String getComments() {
        return comments;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getBoibazar_price() {
        return boibazar_price;
    }

    public String getBoibazar_url() {
        return boibazar_url;
    }

    public String getImage() {
        return image;
    }

    public String getBoibazar_rating() {
        return boibazar_rating;
    }

    public String getBoibazar_noOfRated() {
        return boibazar_noOfRated;
    }

    public String getRokomari_price() {
        return rokomari_price;
    }

    public String getRokomari_rating() {
        return rokomari_rating;
    }

    public String getRokomari_url() {
        return rokomari_url;
    }

    public String getRokomari_noOfrated() {
        return rokomari_noOfrated;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book that = (Book) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(author, that.author) &&
                Objects.equals(boibazar_price, that.boibazar_price) &&
                Objects.equals(image, that.image) &&
                Objects.equals(boibazar_url, that.boibazar_url) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title,author, boibazar_price, image, boibazar_url);
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "title='" + title + '\'' +
                ", url='" + author + '\'' +
                ", url='" + boibazar_price + '\'' +
                ", url='" + image + '\'' +
                ", url='" + boibazar_url + '\'' +
                '}';
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(image, book.image) &&
                Objects.equals(boibazar_price, book.boibazar_price) &&
                Objects.equals(boibazar_url, book.boibazar_url) &&
                Objects.equals(boibazar_rating, book.boibazar_rating) &&
                Objects.equals(boibazar_noOfRated, book.boibazar_noOfRated) &&
                Objects.equals(rokomari_price, book.rokomari_price) &&
                Objects.equals(rokomari_rating, book.rokomari_rating) &&
                Objects.equals(rokomari_url, book.rokomari_url) &&
                Objects.equals(rokomari_noOfrated, book.rokomari_noOfrated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, image, boibazar_price, boibazar_url, boibazar_rating, boibazar_noOfRated, rokomari_price, rokomari_rating, rokomari_url, rokomari_noOfrated);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", image='" + image + '\'' +
                ", boibazar_price='" + boibazar_price + '\'' +
                ", boibazar_url='" + boibazar_url + '\'' +
                ", boibazar_rating='" + boibazar_rating + '\'' +
                ", boibazar_noOfRated='" + boibazar_noOfRated + '\'' +
                ", rokomari_price='" + rokomari_price + '\'' +
                ", rokomari_rating='" + rokomari_rating + '\'' +
                ", rokomari_url='" + rokomari_url + '\'' +
                ", rokomari_noOfrated='" + rokomari_noOfrated + '\'' +
                '}';
    }
}
