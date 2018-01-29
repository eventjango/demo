package com.example.demo.test;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;

@Slf4j
@Controller
public class UserController {

    @GetMapping("/login")
    public String login(){

        String authorizeUrl = "http://localhost:80/oauth/authorize?client_id=clientapp&redirect_uri=http://localhost:80/callback&response_type=code&scope=read_profile";
        SecureRandom secureRandom = new SecureRandom();
        String state = new BigInteger(130, secureRandom).toString();

        authorizeUrl += "&state=".concat(state);

        return "redirect:".concat(authorizeUrl);
    }


    @GetMapping("/callback")
    public String callback(HttpServletRequest request) throws IOException, JSONException{

        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String tokenUrl = "http://localhost:80/oauth/token";

        String params = "code=".concat(code);
        params += "&grant_type=".concat("authorization_code");
        params += "&client_id=".concat("clientapp");
        params += "&client_secret=".concat("123456");
        params += "&scope=".concat("read_profile");
        params += "&redirect_uri=".concat("http://localhost:80/callback");

        HttpURLConnection urlConnection = (HttpURLConnection) new URL(tokenUrl).openConnection();
        String userCredential = "clientapp:123456";
        String basicAuth = "Basic " + new String(new Base64().encode(userCredential.getBytes()));

        urlConnection.setRequestProperty("Authorization", basicAuth);
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        urlConnection.setRequestProperty("Content-Language", "en-US");
        urlConnection.setUseCaches(false);
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);

        urlConnection.connect();

        DataOutputStream out = new DataOutputStream(urlConnection.getOutputStream());
        out.writeBytes(params);
        out.flush();
        out.close();

        int responseCode = urlConnection.getResponseCode();

        log.debug("URL : " + tokenUrl);
        log.debug("Method : " + urlConnection.getRequestMethod());
        log.debug("Post parameters : " + params);
        log.debug("Response Code : " + responseCode);


        BufferedReader in = new BufferedReader(new InputStreamReader(responseCode == 200? urlConnection.getInputStream() : urlConnection.getErrorStream()));

        String line;
        StringBuffer stringBuffer = new StringBuffer();

        while ((line = in.readLine()) != null){
            stringBuffer.append(line);
        }

        in.close();

        JSONObject jsonObject = new JSONObject(stringBuffer.toString());

        HttpSession session = request.getSession();

        session.setAttribute("access_token", jsonObject.getString("access_token"));
        session.setAttribute("token_type", jsonObject.getString("access_token"));
        session.setAttribute("refresh_token", jsonObject.getString("refresh_token"));
        session.setAttribute("expires_in", jsonObject.getString("expires_in"));


        System.out.println(jsonObject);

        return "";
    }


    @GetMapping("/redirected")
    public String redirected(HttpServletRequest request){

        System.out.println(request);
        return "";
    }

    @GetMapping("/api/profile")
    public ResponseEntity<UserInfo> profile(){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String email = user.getUsername() + "@google.com";

        UserInfo userInfo
                = UserInfo
                .builder()
                .name(user.getUsername())
                .email(email)
                .build();

        return ResponseEntity.ok(userInfo);
    }
}
