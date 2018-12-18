package com.jahid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ExtractHtml {
    public static void main(String[] args) {

        String html = "<html><head><title>Sample Title</title></head>"
                + "<body>"
                + "<p>Sample Content</p>"
                + "<div id='sampleDiv'><a href='www.google.com'>Google</a>"
                + "<h3><a>Sample</a><h3>"
                + "</div>"
                + "</body></html>";
        Document document = Jsoup.parse(html);

        // A with href
        Element link = document.select("a").first();

        System.out.println("Outer Html: " + link.outerHtml());
        System.out.println("Inner Html: " + link.html());
    }
}
