/*
package com.example.demo.security;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;

@Controller
@RequestMapping("/oauth-api")
public class OAuthController {

    @Value("${oauth.megazone.clientId}")
    private String clientIdAndMegazone;

    @Value("${oauth.megazone.clientSecret}")
    private String clientSecretAndMegazone;

    @Value("${oauth.megazone.callbackURL}")
    private String callbackURLAndMegazone;

    @GetMapping("/login/{type}")
    public String login(@PathVariable("type") String type, HttpServletRequest request) throws UnsupportedEncodingException {

        switch (type){

            case OAuthClientsFactory.MEGA_ZONE:

                String clientId = clientIdAndMegazone;
                String callbackAddress = "http://localhost:80";
                callbackAddress += "/oauth-api/callback/megazone";

                String redirectURI = URLEncoder.encode(callbackAddress, "UTF-8");

                SecureRandom secureRandom = new SecureRandom();
                String state = new BigInteger(130, secureRandom).toString();

                String url = "http://localhost:80";
                url += "/oauth/authorize?response_type=code";
                url += "&client_id=".concat(clientId);
                url += "&redirect_uri=".concat(redirectURI);
                url += "&state=".concat(state);

                HttpSession session = request.getSession();
                session.setAttribute("state", state);

                return "redirect:" + url;
        }

        return null;
    }


    @GetMapping("/callback/{system}")
    public String oauthCallback(@PathVariable String system, HttpServletRequest request, Model model) throws UnsupportedEncodingException, IOException, JSONException {

        switch (system){

            case OAuthClientsFactory.MEGA_ZONE:

                String api = "http://localhost:80";
                api += "/grant_type=authorization_code";
                api += "&client_id=".concat(clientIdAndMegazone);
                api += "&secret=".concat(clientSecretAndMegazone);
                api += "&code=" + request.getParameter("code");
                api += "&state=" + request.getParameter("state");
                api += "&redirect_uri=" + URLEncoder.encode("http://localhost:80/oauth/token", "UTF-8");

                URL url = new URL(api);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");

                int responseCode = urlConnection.getResponseCode();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseCode == 200? urlConnection.getInputStream() : urlConnection.getErrorStream()));

                String line;
                StringBuffer stringBuffer = new StringBuffer();

                while ((line = bufferedReader.readLine()) != null){
                    stringBuffer.append(line);
                }

                bufferedReader.close();

                JSONObject jsonObject = new JSONObject(stringBuffer.toString());

                HttpSession session = request.getSession();

                session.setAttribute("access_token", jsonObject.getString("access_token"));
                session.setAttribute("token_type", jsonObject.getString("access_token"));
                session.setAttribute("refresh_token", jsonObject.getString("refresh_token"));
                session.setAttribute("expires_in", jsonObject.getString("expires_in"));


                String header = "Bearer " + jsonObject.getString("access_token");

                System.out.println("header : " + header);

                return header;
        }


        return null;
    }




}
*/
