package com.jahid;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
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

public class RokomariReview {
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static String title;
    static String rokomari_link;
    static String review;
    static String summary;

    public static void main(String[] args) {

        final List<BookReview> resultList = new ArrayList<BookReview>();

        System.setProperty("webdriver.gecko.driver", "c:\\geckodriver.exe");
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        FirefoxDriver driver = new FirefoxDriver(firefoxOptions);
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);

        driver.get("https://www.rokomari.com/book/author/930/%E0%A6%B0%E0%A6%AC%E0%A7%80%E0%A6%A8%E0%A7%8D%E0%A6%A6%E0%A7%8D%E0%A6%B0%E0%A6%A8%E0%A6%BE%E0%A6%A5-%E0%A6%A0%E0%A6%BE%E0%A6%95%E0%A7%81%E0%A6%B0?ref=mm_p3");
        int i = 0;
        try {

            String html = driver.getPageSource();
            Document doc = Jsoup.parse(html);

            for (Element row : doc.select("div.book-list-wrapper")) {

                rokomari_link = "https://www.rokomari.com" + row.select("a").attr("href");
                title = row.select("p.book-title").text();

                System.out.println("rokomari link: " + rokomari_link);
                FirefoxDriver reviewDriver = new FirefoxDriver(firefoxOptions);
                reviewDriver.get(rokomari_link);
                reviewDriver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);

                String reviewHtml = reviewDriver.getPageSource();
                Document reviewDoc = Jsoup.parse(reviewHtml);


                for (Element element : reviewDoc.select("div.details-ratings-review")) {

                    review = element.select("div.js--ratings-review__content--review.ratings-review__content--review").select("div.user-review-container--description").text();

                    System.out.println(i++);
                    System.out.println("review: " + title);
                    System.out.println("review: " + review);
                }


                resultList.add(new BookReview(title, summary, review));
                OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("book_review_11.json"), resultList);
                reviewDriver.quit();
            }

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
