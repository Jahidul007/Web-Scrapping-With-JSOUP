# JSOUP
Document document = Jsoup.parse(html);<\br>
Element sampleDiv = document.getElementById("sampleDiv");<\br>
Elements links = sampleDiv.getElementsByTag("a")<\br><\br>
Where<\br>
. document − document object represents the HTML DOM.<\br>
. Jsoup − main class to parse the given HTML String.<\br>
. html − HTML String.<\br>
. sampleDiv − Element object represent the html node element identified by id "sampleDiv".<\br>
. links − Elements object represents the multiple node elements identified by tag "a".<\br>
