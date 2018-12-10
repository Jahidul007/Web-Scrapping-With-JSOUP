package com.jahid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Selector {
    public static void main(String[] args) {

        String html = "<html><head><title>Sample Title</title></head>"
                + "<body>"
                + "<p>Sample Content</p>"
                + "<div id='sampleDiv'><a href='www.google.com'>Google</a>"
                + "<h3><a>Sample</a><h3>"
                + "</div>"
                + "<div id='imageDiv' class='header'><img name='google' src='google.png' />"
                + "<img name='yahoo' src='yahoo.jpg' />"
                + "</div>"
                + "</body></html>";
        Document document = Jsoup.parse(html);

        // a with href
        Elements links = document.select("a[href]");
        for (Element link : links){
            System.out.println("Href: " + link.attr("href"));
            System.out.println("Text: " + link.text());
        }

        // img with src ending .jpg
        Elements pngs = document.select("img[src$=.jpg]");
        for (Element png : pngs){
            System.out.println("Name: " + png.attr("name"));
        }
        // div with class header
        Element headerDiv = document.select("div.header").first();
        System.out.println("Id: " + headerDiv.id());

        // direct a after h3
        Elements sampleLinks = document.select("h3 > a");
        for (Element link : sampleLinks){
            System.out.println("Text: " + link.text());
        }
    }
}
