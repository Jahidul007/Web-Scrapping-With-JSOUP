# JSOUP
Jsoup is a java html parser. It is a java library that is used to parse HTML document. Jsoup provides api to extract and manipulate data from URL or HTML file. It uses DOM, CSS and Jquery-like methods for extracting and manipulating file.</br></br>
```java
Document document = Jsoup.parse(html);</br>
Element sampleDiv = document.getElementById("sampleDiv");</br>
Elements links = sampleDiv.getElementsByTag("a")</br>
System.out.println("Relative Link: " + link.attr("href"));</br>
System.out.println("Absolute Link: " + link.attr("abs:href"));</br>
System.out.println("Absolute Link: " + link.absUrl("href"));</br></br>
```
Where</br>
**document** − document object represents the HTML DOM.</br>
**Jsoup** − main class to parse the given HTML String.</br>
**html** − HTML String.</br>
**sampleDiv** − Element object represent the html node element identified by id "sampleDiv".</br>
**links** − Elements object represents the multiple node elements identified by tag "a".</br>
**link.attr** − provides the value of href present in anchor tag. It may be relative or absolute.</br>
**link.attr** − provides the absolute url after resolving against the document's base URI.</br>
**link.absUrl** − provides the absolute url after resolving against the document's base URI.</br></br>

# Unirest(Ajax/dynamic)
1. Use a headless browser</br>
 - e.g. HtmlUnit For java</br>
 - musch slower</br>
 - easier to detect</br>
2. Reverse engineering and calling the undocumented API directly</br>
 - use the browser's developer tools</br>
 - very fast</br>
 - mostly returns already structured data(XML or JSON)</br>
 
 ## Selenium webdriver (geckoDriver) - Headless Mode
 How to build this project
 1. Download the project and import into Intellij

2. Set the build path which must have the following libraries 

 * [selenium-server-standalone](https://www.seleniumhq.org/download/)
 * [geckodriver](https://github.com/mozilla/geckodriver/releases)

 ## Source code
 ```java
        System.setProperty("webdriver.gecko.driver","c:\\geckodriver.exe");
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        FirefoxDriver driver = new FirefoxDriver(firefoxOptions);
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.get(url);
        // parse html
        String html = driver.getPageSource();
        Document doc = Jsoup.parse(html);
 ```
 ## For Maven User
 ```xml
        <dependency>
            <groupId>com.mashape.unirest</groupId>
            <artifactId>unirest-java</artifactId>
            <version>1.4.9</version>
        </dependency>
        <dependency>
            <!-- jsoup HTML parser library @ https://jsoup.org/ -->
            <groupId>org.jsoup</groupId>
            <artifactId>jsoup</artifactId>
            <version>1.9.1</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.7.4</version>
        </dependency>
```
 
