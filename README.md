# JSOUP
Jsoup is a java html parser. It is a java library that is used to parse HTML document. Jsoup provides api to extract and manipulate data</br> from URL or HTML file. It uses DOM, CSS and Jquery-like methods for extracting and manipulating file.</br>
Document document = Jsoup.parse(html);</br>
Element sampleDiv = document.getElementById("sampleDiv");</br>
Elements links = sampleDiv.getElementsByTag("a")</br>
System.out.println("Relative Link: " + link.attr("href"));</br>
System.out.println("Absolute Link: " + link.attr("abs:href"));</br>
System.out.println("Absolute Link: " + link.absUrl("href"));</br></br>
Where</br>
**document** − document object represents the HTML DOM.</br>
**Jsoup** − main class to parse the given HTML String.</br>
**html** − HTML String.</br>
**sampleDiv** − Element object represent the html node element identified by id "sampleDiv".</br>
**links** − Elements object represents the multiple node elements identified by tag "a".</br>
**link.attr** − provides the value of href present in anchor tag. It may be relative or absolute.</br>
**link.attr** − provides the absolute url after resolving against the document's base URI.</br>
**link.absUrl** − provides the absolute url after resolving against the document's base URI.</br>
