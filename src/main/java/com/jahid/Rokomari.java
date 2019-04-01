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

    public static void main(String[] args) throws Exception {
        final List<Book> resultList = new ArrayList<Book>();

        try {
            Document document = Jsoup.connect("https://www.rokomari.com/book/category/2216/bookfair-2019?page=1").userAgent(USER_AGENT).maxBodySize(0).timeout(0).get();

            for (Element row : document.select("div.book-list-wrapper")) {

                rokomari_link = "https://www.rokomari.com" + row.select("a").attr("href");

                //System.out.println(rokomari_link);


                Document rokomariDocument = Jsoup.connect(rokomari_link).userAgent(USER_AGENT).maxBodySize(0).timeout(0).get();

                for (Element element : rokomariDocument.select("div.details-book-main-info-wrapper")) {

                    title = element.getElementsByTag("h1").text();
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

                            String rating = newelement.getElementsByTag("p").first().text();

                            if (rating.contains("."))
                                rokomari_rating = rating;
                            else
                                rokomari_rating = "0";
                        }

                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Title: " + title);
                    System.out.println("Author: " + author);
                    System.out.println("image: " + imageUrl);
                    System.out.println("Price: " + rokomari_price);
                    System.out.println("Rating: " + rokomari_rating);
                    System.out.println("Rating no: " + rnr);
                    System.out.println();


                }

                /*try {
                    Document document1 = Jsoup.connect("https://www.rokomari.com/search?term=" + title).get();
                    for (Element rowElement : document1.select("div.book-list-wrapper")) {

                        //System.out.println("row : " + document);

                        rokomari_url = "https://www.rokomari.com" + rowElement.getElementsByTag("a").attr("href");
                        String price1 = rowElement.select("p.book-price").get(0).text();
                        String[] words = price1.split("\\s");

                        rokomari_price = words[0] + words[1];

                        Document newDocument1 = Jsoup.connect(rokomari_url).get();
                        //System.out.println("newDocument: " + newDocument);
                        for (Element element : newDocument1.select("div.details-book-main-info-wrapper")) {

                            // final String newprice = element.getElementsByTag("p").get(1).text();
                            rokomari_rating = element.getElementsByTag("span").get(2).text();

                            rokomari_noOfrated = element.getElementsByTag("a").get(2).text();

                            if (rokomari_rating.contains("Ratings") && rokomari_noOfrated.contains("Reviews")) {
                                System.out.println("Rating: " + rokomari_rating);
                                System.out.println("number_of_Rating : " + rokomari_noOfrated);
                            } else {
                                rokomari_rating = "";

                                rokomari_noOfrated = "";
                            }
                            //System.out.println("new Price: " + newprice);
                            System.out.println("rokomari  rating: " + rokomari_rating);
                            System.out.println("rokomari Rating number: " + rokomari_noOfrated);

                        }
                        System.out.println("Title: " + rokomari_url);
                        System.out.println("price: " + rokomari_price);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

                resultList.add(new Book(title, author, rokomari_price, imageUrl, rokomari_link, rokomari_rating,
                        rokomari_numberOfRating, rokomari_price, rokomari_rating, rokomari_numberOfRating, rokomari_link));
                
            }
            OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("book_2k19.json"), resultList);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
