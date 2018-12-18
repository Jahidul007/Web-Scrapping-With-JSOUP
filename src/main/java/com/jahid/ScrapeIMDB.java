package com.jahid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class ScrapeIMDB {
    public static void main(String[] args){

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

    }
}
