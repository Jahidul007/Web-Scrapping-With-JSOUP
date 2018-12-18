package com.jahid;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

    public static void main(String[] args) {
        // write your code here

        String html = "<html><head><title>Sample Title</title><title>Complex Title</title></head>"
                + "<body><p>Sample Content</p><p>Complex Content</p></body></html>";

        Document document = Jsoup.parse(html);

        //System.out.println(document.title());

        Elements element = document.getElementsByTag("p");

        for (Element elements : element) {

            System.out.println(elements.text());

        }
    }
}
