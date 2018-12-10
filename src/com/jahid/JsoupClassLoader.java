package com.jahid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class JsoupClassLoader {
    public static  void main(String[] args) throws IOException, URISyntaxException{

        URL path = ClassLoader.getSystemResource("test.html");
        File input = new File(path.toURI());
        Document document = Jsoup.parse(input, "UTF-8");
        System.out.println(document.title());
    }
}
