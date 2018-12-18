package com.jahid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class SetTextContent {
    public static void main(String[] args) {

        String html = "<html><head><title>Sample Title</title></head>"
                + "<body>"
                + "<div id='sampleDiv'><a id='googleA' href='www.google.com'>Google</a></div>"
                + "</body></html>";
        Document document = Jsoup.parse(html);

        Element div = document.getElementById("sampleDiv");
        System.out.println("Outer html before modification: \n" + div.outerHtml());
        div.text("This is a sample content.");
        System.out.println("Outer html after modification: \n" + div.outerHtml());
        div.prepend("Initial Text.");
        System.out.println("After prepend:\n " + div.outerHtml());
        div.append("End Text.");
        System.out.println("After append:\n " + div.outerHtml());
    }

}
