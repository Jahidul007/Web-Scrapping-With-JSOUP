package com.jahid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class JsoupUrl {
    public static void main(String[] args) throws IOException {

        String url = "http://www.tutorialspoint.com/";

        Document document = Jsoup.connect(url).get();
        Element link = document.select("a").first();

        System.out.println("Relative Link: " + link.attr("href"));
        System.out.println("Absolute Link: " + link.attr("abs:href"));
        System.out.println("Absolute Link: " + link.absUrl("href"));


    }
}
