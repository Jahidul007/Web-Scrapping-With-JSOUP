package com.jahid;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Rokomari {

    // user agent use if http failed
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    static String title;
    static String author;
    static String imageUrl;
    static String rokomari_link;
    static String rokomari_price;
    static String rokomari_rating;
    static String rokomari_numberOfRating;

    static ArrayList<String> arr_linkText = new ArrayList<String>();
    static String boibazar_link;
    static String boibazar_rating = "";
    static String boibazar_rating_number = "";
    static String price = "";
    private static String boibazar_book_title;
    private static String boibazar_book_author;

    public static void main(String[] args) throws Exception {
        final List<Book> resultList = new ArrayList<Book>();

        try {
            Document document = Jsoup.connect("https://www.rokomari.com/book/category/2216/bookfair-2019?page=1").userAgent(USER_AGENT).maxBodySize(0).timeout(0).get();

            for (Element row : document.select("div.book-list-wrapper")) {

                rokomari_link = "https://www.rokomari.com" + row.select("a").attr("href");
                title = row.select("p.book-title").text();

                Document rokomariDocument = Jsoup.connect(rokomari_link).userAgent(USER_AGENT).maxBodySize(0).timeout(0).get();

                for (Element element : rokomariDocument.select("div.details-book-main-info-wrapper")) {

                    //title = element.getElementsByTag("h1").text();
                    author = element.getElementsByTag("a").get(0).text();
                    imageUrl = element.getElementsByTag("img").attr("src");
                    rokomari_price = element.select("p.details-book-info__content-book-price").text();
                    rokomari_numberOfRating = element.getElementsByTag("span").get(2).text();
                    String rnr;
                    if (rokomari_numberOfRating.contains("Rating"))
                        rnr = rokomari_numberOfRating;
                    else
                        rnr = "0";

                    //  rokomari_rating = element.getElementsByTag("a").get(2).attr("href");

                    try {
                        Document rokomariReview = Jsoup.connect(rokomari_link + "#review").userAgent(USER_AGENT).maxBodySize(0).timeout(0).get();
                        for (Element newelement : rokomariReview.select("div.ratings-review__content--rating")) {

                            String rating = newelement.select("h2.pt-2").text();

                            if (rating.contains("."))
                                rokomari_rating = rating;
                            else
                                rokomari_rating = "0";
                        }

                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                    /*System.out.println("Title: " + title);
                    System.out.println("Author: " + author);
                    System.out.println("Link: " + rokomari_link);
                    System.out.println("image: " + imageUrl);
                    System.out.println("Price: " + rokomari_price);
                    System.out.println("Rating: " + rokomari_rating);
                    System.out.println("Rating no: " + rnr);
                    System.out.println();*/

                    try {

                        Document boibazar = Jsoup.connect("https://www.boibazar.com/search?_token=iyzCe62oMKyeELRifbh5ifu4EmmfcO2icvAemiiX&selected_search_type=products&searchTerm=" + title).maxBodySize(0).timeout(0).get();

                        for (Element search : boibazar.select("div.row.search-border")) {
                            String book_title = search.select("h1").text();
                            String book_author = search.select("a.nameSize").get(1).text();
                            String book_author_name = book_title + " " + book_author;


                            if (book_title.contains(title) && book_author.contains(author)) {
                                arr_linkText.add(book_author_name);
                                boibazar_book_title = book_title;
                                boibazar_book_author = book_author;
                                boibazar_link = search.getElementsByTag("a").attr("href");
                            } else {
                                boibazar_book_title = "";
                                boibazar_book_author = "";
                                // boibazar_link = "";
                            }

                            if (boibazar_link.contains("boibazar")) {
                                Document document1 = Jsoup.connect(boibazar_link).maxBodySize(0).timeout(0).get();

                                for (Element element1 : document1.select("div.book-details-section")) {

                                    boibazar_rating = element1.getElementsByTag("span").get(2).text();

                                    boibazar_rating_number = element1.getElementsByTag("span").get(3).text();
                                    price = element1.select("span.price-font").get(0).text();


                                    if (boibazar_rating.contains("Ratings")) {
                                        System.out.println("Boibazar Rating:  " + boibazar_rating);
                                        System.out.println("Boibazar number_of_Rating : " + boibazar_rating_number);
                                    } else {
                                        boibazar_rating = "";
                                        boibazar_rating_number = "";
                                    }

                                }
                            } else {
                                boibazar_rating = "";
                                boibazar_rating_number = "";
                                price = "";
                                boibazar_link = "";

                            }
                        }
                        // System.out.println(arr_linkText);
                        System.out.println("Link: " + boibazar_link);
                        System.out.println("Price: " + price);


                    } catch (NullPointerException | IOException ne) {
                        ne.printStackTrace();
                    }


                }

                resultList.add(new Book(title, author, price, imageUrl, boibazar_link, boibazar_rating,
                        boibazar_rating_number, rokomari_price, rokomari_rating, rokomari_link, rokomari_numberOfRating));
            }
            OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("book_2k19.json"), resultList);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
