package io.javabrains.restAPI.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class restAPI { 

    private final String URL_WORKING = "Site is up and working";
    private final String URL_NOT_WORKING = "Site is down.. ";
    private final String URL_INCORRECT = "URL is incorrect..";

    @RequestMapping("/check")
    public String checkCheck() {
        return "This is a Check route..";
    }

    @GetMapping("/test")
    public String testRoute() {
        return "This is a test route..";
    }

    @GetMapping("/checkUrl")
    public String getUrlStatus(@RequestParam String url) {

        String returnMessage = "";

        try {
            URL urlObject = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObject.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode() / 100;
            if (responseCode != 2 || responseCode != 3) {
                returnMessage = URL_NOT_WORKING;
            } else {
                returnMessage = URL_WORKING;
            }
        } catch (MalformedURLException e) {
            returnMessage = URL_INCORRECT;
            System.out.println("try-catch-error: " + e);
        } catch (IOException e) {
            returnMessage = URL_NOT_WORKING;
            System.out.println("addnl-catch-error: " + e);
        }

        return returnMessage;
    }
}
