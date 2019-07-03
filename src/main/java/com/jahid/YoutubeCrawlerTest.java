package com.jahid;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    static String html;
    static String html1;
    static ArrayList<String> userComments;


    public static void main(String[] args) throws InterruptedException {

        final List<Link> linkList = new ArrayList<>();
        final List<YoutubeModel> resultList = new ArrayList<>();
        final List<YoutubeDetails> detailsList = new ArrayList<>();

        /*System.setProperty("webdriver.gecko.driver", "c:\\geckodriver.exe");
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        //firefoxBinary.addCommandLineOptions("--headless");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);*/

        System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--mute-audio");
        WebDriver driver = new ChromeDriver();
        WebDriver reviewDriver = new ChromeDriver(chromeOptions);



        /*FirefoxDriver driver = new FirefoxDriver(firefoxOptions);
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);

        FirefoxDriver reviewDriver = new FirefoxDriver(firefoxOptions);
        reviewDriver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
*/
        Thread.sleep(5000);
        driver.get("https://www.youtube.com/results?search_query=songs");

        try {
            long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

            System.out.println(lastHeight);
            while (true) {
                for (int i = 0; i < 300; i++) {
                    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 500000);");
                    html = driver.getPageSource();

                }
                System.out.println(html);
                Thread.sleep(10);

                long newHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
                if (newHeight == lastHeight) {
                    break;
                }
                lastHeight = newHeight;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            // html = driver.getPageSource();
            Document doc = Jsoup.parse(html);


            int id = 0;
            for (Element alink : doc.select("ytd-video-renderer.style-scope.ytd-item-section-renderer")) {
                id = id + 1;

                System.out.println("id: " + id);
                link = "https://www.youtube.com" + alink.select("a").attr("href");

                System.out.println("Link: " + link);


                Thread.sleep(1000);
                linkList.add(new Link(id,link));
                OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("link2.json"), linkList);

               /* reviewDriver.get(link);
               // System.out.println("link" + link);

                ((JavascriptExecutor) reviewDriver).executeScript("window.scrollTo(0, 2500);");

                Thread.sleep(1000);
                try {
                    long lastHeight = (long) ((JavascriptExecutor) reviewDriver).executeScript("return document.body.scrollHeight");

                    while (true) {
                        for (int i = 0; i < 1000; i++) {
                            ((JavascriptExecutor) reviewDriver).executeScript("window.scrollTo(0, 500000);");
                            html1 = reviewDriver.getPageSource();
                        }
                        Thread.sleep(4000);
                        long newHeight = (long) ((JavascriptExecutor) reviewDriver).executeScript("return document.body.scrollHeight");
                        if (newHeight == lastHeight) {
                            break;
                        }
                        lastHeight = newHeight;
                    }


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
                }catch (Exception io) {
                    io.printStackTrace();
                }*/

            }
            //OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("commentsJAVA.csv"), resultList);
          //  OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("detailsJAVA.csv"), detailsList);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            driver.quit();
            reviewDriver.quit();
        }
    }
}
