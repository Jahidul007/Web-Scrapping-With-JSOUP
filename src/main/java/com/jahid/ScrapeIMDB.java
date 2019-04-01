package com.jahid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScrapeIMDB {

    final List<Book> resultList = new ArrayList<Book>();
    String boibazar_rating = "";
    String boibazar_rating_number = "";

    static String rokomari_price = "";
    static String rokomari_url = "";
    static String rokomari_rating = "";
    static String rokomari_noOfrated = "";
    public static void main(String[] args) {

        try {
            Document document = Jsoup.connect("https://www.imdb.com/chart/top").get();
            for(Element row : document.select("table.chart.full-width tr")){

                String title = row.select(".titleColumn a").text();
                String rating = row.select(".imdbRating").text();

                System.out.println(title + "-> Rating: " + rating);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*try {
            Document document1 = Jsoup.connect("https://www.rokomari.com/search?term=" + "হাইজেনবার্গের গল্প").get();
            for (Element rowElement : document1.select("div.browse__content-books-wrapper")) {

                //System.out.println("row : " + document);
                String new_div = rowElement.select("div.row").get(0).text();
                System.out.println("New div: "+new_div);

                rokomari_url = "https://www.rokomari.com" + rowElement.getElementsByTag("a").attr("href");
                String price1 = rowElement.select("p.book-price").get(0).text();
                String[] words = price1.split("\\s");

                rokomari_price = words[0] + words[1];

                Document newDocument1 = Jsoup.connect(rokomari_url).get();
                //System.out.println("newDocument: " + newDocument);
                for (Element element : newDocument1.select("div.details-book-info__content-rating")) {

                    // final String newprice = element.getElementsByTag("p").get(1).text();

                    rokomari_rating = element.select("span").first().text();
                    System.out.println("rokomari : " + rokomari_rating);
                    rokomari_noOfrated = element.getElementsByTag("a").first().text();


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
*/
    }
}
