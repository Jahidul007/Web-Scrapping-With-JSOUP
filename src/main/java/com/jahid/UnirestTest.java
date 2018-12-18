package com.jahid;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.apache.http.HttpHost;
import org.json.JSONObject;

public class UnirestTest {
    public static void main(String[] args) throws Exception {

        // change the default user-agent header
        Unirest.setDefaultHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");

        //use a proxy for hiding real entity

        //61.135.217.12

        Unirest.setProxy(new HttpHost("185.158.218.29", 53281));

        //Making a simple get request to httpbin.org
        //final HttpResponse<String> response = Unirest.get("http://httpbin.org/get").queryString("limit",10).asString();
        final HttpResponse<JsonNode> getResponse = Unirest.get("http://httpbin.org/get").queryString("limit",10).asJson();

        System.out.println("IP-Address: "+ getResponse.getBody().getObject().getString("origin"));
        System.out.println("User-Agent: "+ getResponse.getBody().getObject().getJSONObject("headers").getString("User-Agent"));

        System.out.println("---------------------------------------------------");
        // Make a post request with form data
        final HttpResponse<String> postResponse = Unirest.post("http://httpbin.org/post").field("postalcode",12345).asString();
        System.out.println(postResponse.getBody());

        System.out.println("---------------------------------------------------");
        // make a post request with body data(json)
        final JSONObject exampleJson = new JSONObject().put("postalcode",1234567);
        final HttpResponse<String> postResponseTwo = Unirest.post("http://httpbin.org/post").body(exampleJson).asString();
        System.out.println(postResponseTwo.getBody());
    }
}
