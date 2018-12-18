package com.jahid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupConnect {
    public static  void main(String[] args) throws IOException {

        String url = "http://www.tutorialspoint.com";
        String url1 = "http://www.google.com";
        Document document = Jsoup.connect(url1).get();

        System.out.println(document.title());
    }
}
