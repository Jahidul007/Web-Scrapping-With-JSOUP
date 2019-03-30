package com.jahid;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Rokomari {

    // user agent use if http failed
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    static String title;

    public static void main(String[] args) throws Exception {

        final List<Book> resultList = new ArrayList<Book>();
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

                    final String rating = element.getElementsByTag("span").get(2).text();

                    final String rating_number = element.getElementsByTag("span").get(3).text();

                    if (rating.contains("Ratings")) {
                        System.out.println("Rating: " + rating);
                        System.out.println("number_of_Rating : " + rating_number);
                    }
                    else {
                        System.out.println("Rating: " + "");

                        System.out.println("number_of_Rating : " + "");
                    }

                }


                System.out.println(title);
                System.out.println(author);
                System.out.println(price);
                System.out.println(image);
                System.out.println(url);


                resultList.add(new Book(title, author, price, image, url));

            }
            OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("boibazar.json"), resultList);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Document document = Jsoup.connect("https://www.rokomari.com/search?term="+title).get();
            for(Element row : document.select("div.book-list-wrapper")){

                //System.out.println("row : " + document);

                String url_rokomari ="https://www.rokomari.com"+ row.getElementsByTag("a").attr("href");
                String price = row.select("p.book-price").get(0).text();
                String[] words=price.split("\\s");

                String rokomari_price = words[0]+words[1];

                Document newDocument = Jsoup.connect(url_rokomari).get();
                //System.out.println("newDocument: " + newDocument);
                for (Element element : newDocument.select("div.details-book-main-info-wrapper")) {

                    // final String newprice = element.getElementsByTag("p").get(1).text();
                    final String rating = element.getElementsByTag("span").get(2).text();

                    final String rating_number = element.getElementsByTag("a").get(2).text();

                    if (rating.contains("Ratings") && rating_number.contains("Reviews")) {
                        System.out.println("Rating: " + rating);
                        System.out.println("number_of_Rating : " + rating_number);
                    }
                    else {
                        System.out.println("Rating: " + "");

                        System.out.println("number_of_Rating : " + "");
                    }
                    //System.out.println("new Price: " + newprice);
                    System.out.println("rokomari  rating: " + rating);
                    System.out.println("rokomari Rating number: " + rating_number);

                }
                System.out.println("Title: " +url_rokomari);
                System.out.println("price: " +rokomari_price);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
