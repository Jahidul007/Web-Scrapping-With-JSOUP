package com.jahid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class SetHtml {
    public static void main(String[] args) {

        String html = "<html><head><title>Sample Title</title></head>"
                + "<body>"
                + "<div id='sampleDiv'><a id='googleA' href='www.google.com'>Google</a></div>"
                + "</body></html>";

        Document document = Jsoup.parse(html);

        Element div = document.getElementById("sampleDiv");
        System.out.println("Outer Html before modification: \n" + div.outerHtml());
        div.html("<p>This is a simple content.</p>");
        System.out.println("Outer Html after modification: \n" + div.outerHtml());
        div.prepend("<p>Initial Text</p>");
        System.out.println("After prepend:\n " + div.outerHtml());
        div.append("<p>End Text</p>");
        System.out.println("After append:\n " + div.outerHtml());
    }
}
