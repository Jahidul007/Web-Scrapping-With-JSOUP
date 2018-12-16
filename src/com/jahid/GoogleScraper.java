package com.jahid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.PrintWriter;
import java.net.URLEncoder;

public class GoogleScraper {

    // user agent use if http failed
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36";

    public static void main(String[] args) throws Exception {

        String query = "imac buy";
        // Document document = Jsoup.connect("https://www.google.com/search?q="+ URLEncoder.encode(query,"UTF-8")).userAgent(USER_AGENT).get();
        Document document = Jsoup.connect("https://www.google.com/search?q=" + URLEncoder.encode(query, "UTF-8")).get();

        //System.out.println(document.outerHtml());

        final PrintWriter out = new PrintWriter("results.txt");

        for (Element searchResult : document.select("div.r")) {

            //final String title = searchResult.text();
            final String title = searchResult.getElementsByTag("h3").text();
            final String url = searchResult.getElementsByTag("a").attr("href");


            System.out.println(title + " -> " + url);
            System.out.println("...........................................");

            out.write(title + " -> " + url + "\n");

        }
        out.close();

    }
}
