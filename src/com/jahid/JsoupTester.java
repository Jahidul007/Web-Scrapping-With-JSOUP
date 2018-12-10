package com.jahid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTester {
    public static void main(String[] args){

        String html = "<div><p>Sample Content<p><div>";

        Document document = Jsoup.parseBodyFragment(html);

        Element body = document.body();
        Elements paragraphs = body.getElementsByTag("p");

        for(Element element : paragraphs){

            System.out.println(element.text());
        }
    }
}
