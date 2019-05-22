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


import java.util.concurrent.TimeUnit;

public class YoutubeTest {
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static String title;
    static String link;
    static String noOfViews = "";
    static String noOfComments = "";
    static String comments = "";


    public static void main(String[] args) {

        System.setProperty("webdriver.gecko.driver", "c:\\geckodriver.exe");

        FirefoxBinary firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        FirefoxDriver driver = new FirefoxDriver(firefoxOptions);
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);

        FirefoxDriver reviewDriver = new FirefoxDriver(firefoxOptions);

        driver.get("https://www.youtube.com/watch?v=rj0wDn5ZsVY&list=PLdZgtDJATfC_cEkmpmluv0TnaSh4wKE5J&index=2&t=0s");

        JavascriptExecutor jse = (JavascriptExecutor) driver;

        jse.executeScript("window.scroll(1, 500)");


        /// now wait let load the comments
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jse.executeScript("window.scroll(1, 3000)");
        try {

            String html = driver.getPageSource();
            Document doc = Jsoup.parse(html);



            Elements newDoc = doc.select("#contents");
            jse.executeScript("window.scroll(1, 3000)");

            Elements info = doc.select("#primary-inner");

            Elements view = info.select("#container");
            title = view.select("h1.style-scope.ytd-video-primary-info-renderer").text();
            noOfViews = view.select("span.view-count.style-scope.yt-view-count-renderer").text();

            Elements meta = doc.select("#meta-contents");
            String pubDate = meta.select("span.date.style-scope.ytd-video-secondary-info-renderer").text();
            String channelName = meta.select("a.yt-simple-endpoint.style-scope.yt-formatted-string").get(0).text();


            System.out.println("title: " + title);
            System.out.println("NoOfViews: " + noOfViews);
            System.out.println("Pub date: " + pubDate);
            System.out.println("channel Name: " + channelName);


            for (Element alink : newDoc.select("#content-text")) {

               // System.out.println(alink.text());
            }


        } finally {
            driver.close();
        }
    }
}
