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

class YoutubrPlaylist {
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



        System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--mute-audio");
        WebDriver driver = new ChromeDriver();
        WebDriver reviewDriver = new ChromeDriver(chromeOptions);

        Thread.sleep(5000);
        driver.get("https://www.youtube.com/results?search_query=bangla&sp=EgIQAw%253D%253D");

        try {
            long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

            System.out.println("ok");

            while (true) {
                for (int i = 0; i < 300; i++) {
                    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 500000);");
                    html = driver.getPageSource();

                }
                Thread.sleep(10);

                long newHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
                if (newHeight == lastHeight) {
                    break;
                }

                System.out.println("ok");
                lastHeight = newHeight;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            // html = driver.getPageSource();
            Document doc = Jsoup.parse(html);


            int id = 0;
            for (Element alink : doc.select("ytd-playlist-renderer.style-scope.ytd-item-section-renderer")) {
                id = id + 1;

                System.out.println("id: " + id);
                link = "https://www.youtube.com" + alink.select("a").attr("href");

                System.out.println("Link: " + link);


                Thread.sleep(1000);
                linkList.add(new Link(id,link));
                OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("playlistLink.json"), linkList);



                System.out.println("ok");

            }
            //OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("commentsJAVA.csv"), resultList);
            //  OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("detailsJAVA.csv"), detailsList);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            driver.quit();
            reviewDriver.quit();
            System.out.println("ok");
        }
    }
}
