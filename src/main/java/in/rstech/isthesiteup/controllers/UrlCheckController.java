package in.rstech.isthesiteup.controllers;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class UrlCheckController {

    private final String SITE_IS_UP = "Site is working!";
    private final String SITE_IS_DOWN = "Site is down!";
    private final String WRONGURL = "Url is incorrect!";
    

    @GetMapping("/check")
    public String getUrlStatuString(@RequestParam String param) {

        try {
            URL obj = new URL(param);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int respcode = conn.getResponseCode()/100;
            if(respcode != 2 && respcode != 3) {
                return SITE_IS_DOWN;
            }
            else {
                return SITE_IS_UP;
            }
        } catch (MalformedURLException e) {
            return WRONGURL;
        } catch (IOException e) {
            return SITE_IS_DOWN;
        }
    }

}
