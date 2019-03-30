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

    public static void main(String[] args) throws Exception {

        final List<Book> resultList = new ArrayList<Book>();
        String boibazar_rating = null;
        String boibazar_rating_number = null;

        String rokomari_price = "";
        String rokomari_url = "";
        String rokomari_rating = "";
        String rokomari_noOfrated = "";
        try {
            Document document = Jsoup.connect("https://www.boibazar.com/category-books/boimela-2019").get();

            for (Element row : document.select("div.thumbnail-custom")) {

                ///System.out.println(image1);
                title = row.getElementsByTag("p").first().text();
                final String author = row.getElementsByTag("p").get(1).text();
                final String price = row.select("span.tk_home").text();
                final String image = row.getElementsByTag("img").attr("lsrc");
                final String url = row.getElementsByTag("a").attr("href");


                Document newDocument = Jsoup.connect(url).get();
                for (Element element : newDocument.select("div.book-details-section")) {

                    boibazar_rating = element.getElementsByTag("span").get(2).text();

                    boibazar_rating_number = element.getElementsByTag("span").get(3).text();

                    if (boibazar_rating.contains("Ratings")) {
                        System.out.println("Rating: " + boibazar_rating);
                        System.out.println("number_of_Rating : " + boibazar_rating_number);
                    } else {
                        System.out.println("Rating: " + "");

                        System.out.println("number_of_Rating : " + "");
                    }

                }

                try {
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
                                System.out.println("Rating: " + "");

                                System.out.println("number_of_Rating : " + "");
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
                }


                System.out.println(title);
                System.out.println(author);
                System.out.println(price);
                System.out.println(image);
                System.out.println(url);
                System.out.println(boibazar_rating);
                System.out.println(boibazar_rating_number);
                System.out.println(rokomari_url);
                System.out.println(rokomari_price);
                System.out.println(rokomari_rating);
                System.out.println(rokomari_noOfrated);


                resultList.add(new Book(title, author, price, image, url, boibazar_rating,
                        boibazar_rating_number, rokomari_price, rokomari_rating, rokomari_url, rokomari_noOfrated));

            }
            OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("boibazar.json"), resultList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
