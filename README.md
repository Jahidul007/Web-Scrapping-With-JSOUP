# JSOUP
Document document = Jsoup.parse(html);
Element sampleDiv = document.getElementById("sampleDiv");
Elements links = sampleDiv.getElementsByTag("a")
Where
. document − document object represents the HTML DOM.
. Jsoup − main class to parse the given HTML String.
. html − HTML String.
. sampleDiv − Element object represent the html node element identified by id "sampleDiv".
. links − Elements object represents the multiple node elements identified by tag "a"..
