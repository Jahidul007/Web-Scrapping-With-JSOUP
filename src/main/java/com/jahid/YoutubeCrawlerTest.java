package com.jahid;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class YoutubeCrawlerTest {
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static String title;
    static String link;
    static String noOfViews;
    static String noOfComments;
    static String comments;
    static String channelName;
    static String publishedDate;
    static String category;


    public static void main(String[] args) {

        final List<YoutubeModel> resultList = new ArrayList<>();
        final List<YoutubeDetails> detailsList = new ArrayList<>();

        System.setProperty("webdriver.gecko.driver", "c:\\geckodriver.exe");
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        FirefoxDriver driver = new FirefoxDriver(firefoxOptions);
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);

        FirefoxDriver reviewDriver = new FirefoxDriver(firefoxOptions);

        // driver.get("https://www.youtube.com/playlist?list=PLgH5QX0i9K3oAZUB2QXR-dZac0c9HNyRa");
        driver.get("https://www.youtube.com/results?search_query=java+bangla+tutorial&sp=CAM%253D");

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0, 500000)");

        /// now wait let load the url
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        jse.executeScript("window.scrollTo(0, 500000)");
        try {


            String html = driver.getPageSource();
            Document doc = Jsoup.parse(html);


            int id = 0;
            for (Element alink : doc.select("ytd-video-renderer.style-scope.ytd-item-section-renderer")) {
                id = id + 1;

                System.out.println("id: " + id);
                link = "https://www.youtube.com" + alink.select("a").attr("href");

                // System.out.println("Title: " + title);
                System.out.println("Link: " + link);

                reviewDriver.get(link);

                JavascriptExecutor jse1 = (JavascriptExecutor) reviewDriver;

                jse1.executeScript("window.scroll(0, 500000)");


                /// now wait let load the comments
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                jse1.executeScript("window.scroll(0, 500000)");

                String html1 = reviewDriver.getPageSource();
                Document doc1 = Jsoup.parse(html1);

                Elements newDoc = doc1.select("#contents");

                Elements info = doc1.select("#primary-inner");

                Elements view = info.select("#container");
                title = view.select("h1.style-scope.ytd-video-primary-info-renderer").text();
                noOfViews = view.select("span.view-count.style-scope.yt-view-count-renderer").text();

                Elements meta = doc1.select("#meta-contents");
                publishedDate = meta.select("span.date.style-scope.ytd-video-secondary-info-renderer").text();
                channelName = meta.select("a.yt-simple-endpoint.style-scope.yt-formatted-string").get(0).text();

                System.out.println("title: " + title);

                ArrayList<String> userComments;
                userComments = new ArrayList<>();
                int commentId = 0;
                for (Element link : newDoc.select("#content-text")) {

                    commentId = commentId + 1;
                    // System.out.println(link.text());
                    comments = link.text();
                    userComments.add(comments);

                }
                System.out.println("UserComments: " + userComments);

                System.out.println(id);
                resultList.add(new YoutubeModel(id, link, userComments));
                detailsList.add(new YoutubeDetails(id, title, link, noOfViews, channelName, publishedDate));
            }
            OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("comments.json"), resultList);
            OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("details.json"), detailsList);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            driver.quit();
            reviewDriver.quit();
        }
    }
}
