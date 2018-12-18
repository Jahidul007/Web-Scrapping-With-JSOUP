package com.jahid;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.PrintWriter;
import java.net.URLEncoder;

public class ExportCSV {
    public static void main(String[] args) throws Exception {

        String query = "apple";
        Document document = Jsoup.connect("https://www.google.com/search?q=" + URLEncoder.encode(query, "UTF-8")).get();

        //System.out.println(document.outerHtml());

        // export the data as csv
        final PrintWriter out = new PrintWriter("result.csv");
        out.write("Title: URL\n");
        for (Element searchResult : document.select("h3.r a")) {

          final String title = searchResult.text();
          final String url = searchResult.attr("href");

          System.out.println(title  + " -> " +url);

          out.write(title  + "; " +url+"\n");

        }
    }
}

