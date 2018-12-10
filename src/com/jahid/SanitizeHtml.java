package com.jahid;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

// sanitize will showcase prevention of XSS attacks or cross-site scripting attack.
public class SanitizeHtml {
    public static void main(String[] args) {

        String html = "<p><a href='http://example.com/'"
                + "onclick='checkData()'>Link</a></p>";
        System.out.println("Initial HTML: " + html);
        // safeHtml − Cleaned HTML
        // Whitelist − Object to provide default configurations to safeguard html
        // clean − cleans the html using Whitelist.
        String safeHtml = Jsoup.clean(html, Whitelist.basic());
        System.out.println("Cleaned HTML: " + safeHtml);
    }
}
