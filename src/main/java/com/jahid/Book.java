package com.jahid;

import java.util.Objects;

public class Book {

    private final String title;
    private final String author;
    private final String price;
    private final String image;
    private final String url;

    public Book(String title, String author, String price, String image, String url) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.image = image;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book that = (Book) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(author, that.author) &&
                Objects.equals(price, that.price) &&
                Objects.equals(image, that.image) &&
                Objects.equals(url, that.url) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title,author, price, image, url);
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "title='" + title + '\'' +
                ", url='" + author + '\'' +
                ", url='" + price + '\'' +
                ", url='" + image + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
