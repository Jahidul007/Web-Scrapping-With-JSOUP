package com.jahid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;


import java.util.concurrent.TimeUnit;

public class YoutubeLinkCollector {
    static String link;
    static String html;

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.youtube.com/results?search_query=java+bangla+tutorial&sp=CAM%253D");

        try {
            long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

            while (true) {
                for (int i = 0;i<110 ;i++) {
                    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 50000);");
                     html = driver.getPageSource();
                }
                Thread.sleep(2000);

                long newHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
                if (newHeight == lastHeight) {
                    break;
                }
                lastHeight = newHeight;
            }


            Document doc = Jsoup.parse(html);
            int id = 0;
            for (Element alink : doc.select("ytd-video-renderer.style-scope.ytd-item-section-renderer")) {
                link = "https://www.youtube.com" + alink.select("a").attr("href");
                id += 1;
                System.out.println("Link" + id + ": " + link);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
           // driver.quit();

        }
    }
}
