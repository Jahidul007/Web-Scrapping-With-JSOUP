package com.jahid;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ExportJson {

    public static final String USER_AGENT ="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36";

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void main(String[] args) throws IOException {

        String query = "apple";
        Document document = Jsoup.connect("https://www.google.com/search?q=" + URLEncoder.encode(query, "UTF-8")).userAgent(USER_AGENT).get();

        //System.out.println(document.outerHtml());

        final List<SearchResult> resultList = new ArrayList<SearchResult>();

        for (Element searchResult : document.select("h3.r a")) {

            final String title = searchResult.text();
            final String url = searchResult.attr("href");

            resultList.add(new SearchResult(title, url));
        }

        OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("results.json"), resultList);

    }
}

