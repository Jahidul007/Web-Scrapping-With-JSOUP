package com.jahid;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class YoutubeCrawler {
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static String title;
    static String link;
    static String noOfViews;
    static String noOfComments;
    static String comments;


    public static void main(String[] args) {

        final List<YouTube> resultList = new ArrayList<YouTube>();

        System.setProperty("webdriver.gecko.driver", "c:\\geckodriver.exe");
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        FirefoxDriver driver = new FirefoxDriver(firefoxOptions);
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);

        FirefoxDriver reviewDriver = new FirefoxDriver(firefoxOptions);

        driver.get("https://www.youtube.com/playlist?list=PLgH5QX0i9K3oAZUB2QXR-dZac0c9HNyRa");

        try {

            String html = driver.getPageSource();
            Document doc = Jsoup.parse(html);

            for (Element alink : doc.select("ytd-playlist-video-renderer.style-scope.ytd-playlist-video-list-renderer")) {

                link = "https://www.youtube.com" + alink.select("a").attr("href");

                // System.out.println("Title: " + title);
                //   System.out.println("Link: " + link);

                reviewDriver.get(link);
                reviewDriver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);

                System.out.println("Link: " +link);
                String reviewHtml = reviewDriver.getPageSource();
                Document reviewDoc = Jsoup.parse(reviewHtml);
                for (Element row : reviewDoc.select("#primary-inner")) {

                    title = row.select("h1.title.style-scope.ytd-video-primary-info-renderer").text();
                    noOfViews = row.select("span.view-count.style-scope.yt-view-count-renderer").text();
                }

                resultList.add(new YouTube(title, link, noOfViews, noOfComments, comments));
            }
            OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("youtube.json"), resultList);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            driver.quit();
            reviewDriver.quit();
        }
    }
}
