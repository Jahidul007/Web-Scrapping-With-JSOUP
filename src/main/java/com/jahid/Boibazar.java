package com.jahid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class Boibazar extends ArrayList<String> {

    static ArrayList<String> arr_linkText = new ArrayList<String>();
    static String boibazar_link;
    static String boibazar_rating = "";
    static String boibazar_rating_number = "";
    static String price = "";

    public static void main(String[] args) {

        try {

            Document boibazar = Jsoup.connect("https://www.boibazar.com/search?_token=iyzCe62oMKyeELRifbh5ifu4EmmfcO2icvAemiiX&selected_search_type=products&searchTerm=" + "প্যারাডক্সিক্যাল সাজিদ ২").maxBodySize(0).timeout(0).get();

            for (Element search : boibazar.select("div.row.search-border")) {
                String boibazar_book_title = search.select("h1").text();
                String boibazar_book_author = search.select("a.nameSize").get(1).text();
                String book_author = boibazar_book_title + " " + boibazar_book_author;


                if (boibazar_book_title.contains("প্যারাডক্সিক্যাল সাজিদ ২") && boibazar_book_author.contains("আরিফ আজাদ")) {
                    arr_linkText.add(book_author);
                     boibazar_link = search.getElementsByTag("a").attr("href");
                }

                Document document = Jsoup.connect(boibazar_link).maxBodySize(0).timeout(0).get();

                for (Element element : document.select("div.book-details-section")) {

                    boibazar_rating = element.getElementsByTag("span").get(2).text();

                    boibazar_rating_number = element.getElementsByTag("span").get(3).text();
                    price = element.select("span.price-font").get(0).text();


                    if (boibazar_rating.contains("Ratings")) {
                        System.out.println("Boibazar Rating: " + boibazar_rating);
                        System.out.println("Boibazar number_of_Rating : " + boibazar_rating_number);
                    } else {
                        boibazar_rating = "";

                        boibazar_rating_number = "";
                    }

                }

            }
           // System.out.println(arr_linkText);
            System.out.println("Link: " + boibazar_link);
            System.out.println("Price: " + price);


        } catch (NullPointerException | IOException ne) {
            ne.printStackTrace();
        }
    }
}
