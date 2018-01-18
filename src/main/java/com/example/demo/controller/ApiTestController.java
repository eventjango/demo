package com.example.demo.controller;


import lombok.Data;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;

@RestController
public class ApiTestController {

    @Data
    private static class RestMessage{

        private String message;

        public RestMessage(String message){
            this.message = message;
        }
    }


    /**
     * 졸라 아무 권한 없어도 와라 좀.
     * @param request
     * @return
     */
    @GetMapping("/token/test")
    public RestMessage hello(HttpServletRequest request) throws IOException {

        /*Enumeration<String> headerNames =  request.getHeaderNames();

        while (headerNames.hasMoreElements()){
            System.out.println(headerNames.nextElement());
        }

        System.out.println("authorization : " + request.getHeader("authorization"));*/

        String url = "http://localhost:80/oauth/token";
        String urlParams = "grant_type=password&username=admin&password=admin";
        URL urlObject = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

        connection.setRequestProperty("Authorization", "Basic " + Base64.encodeBase64("trusted_app:secret".getBytes()));

        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.connect();


        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(urlParams);
        out.flush();
        out.close();

        int responseCode = connection.getResponseCode();
        System.out.println("URL : " + url);
        System.out.println("Method : " + connection.getRequestMethod());
        System.out.println("Post parameters : " + urlParams);
        System.out.println("Response Code : " + responseCode);

        connection.getHeaderFields()
                .entrySet()
                .forEach(System.out::println);

       /* ClientInfo clientInfo = new ClientInfo();
        clientInfo.setTokenUrl("http://localhost:80/oauth/token");
        clientInfo.setGrant("password");
        clientInfo.setUsername("admin");
        clientInfo.setPassword("admin");

        Map<String, Object> getApi = OkHttpConnector.getTokenApi(clientInfo);*/


       /* BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;
        StringBuffer stringBuffer = new StringBuffer();

        while((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
        }

        bufferedReader.close();

        System.out.println(stringBuffer.toString());*/

        return new RestMessage("responseCode : " + responseCode);
    }



    @GetMapping("/token/test2")
    public RestMessage hello2(HttpServletRequest request) throws IOException, JSONException {

        String url = "http://localhost:80/oauth/token";
        String urlParams = "grant_type=password&username=admin&password=admin";

        URL myURL = new URL(url);

        HttpURLConnection myURLConnection = (HttpURLConnection) myURL.openConnection();
        String userCredential = "trusted_app:secret";
        String basicAuth = "Basic " + new String(new Base64().encode(userCredential.getBytes()));

        myURLConnection.setRequestProperty("Authorization", basicAuth);
        myURLConnection.setRequestMethod("POST");
        myURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        myURLConnection.setRequestProperty("Content-Language", "en-US");
        myURLConnection.setUseCaches(false);
        myURLConnection.setDoInput(true);
        myURLConnection.setDoOutput(true);

        myURLConnection.connect();


        DataOutputStream out = new DataOutputStream(myURLConnection.getOutputStream());
        out.writeBytes(urlParams);
        out.flush();
        out.close();

        int responseCode = myURLConnection.getResponseCode();
        System.out.println("URL : " + url);
        System.out.println("Method : " + myURLConnection.getRequestMethod());
        System.out.println("Post parameters : " + urlParams);
        System.out.println("Response Code : " + responseCode);

        myURLConnection.getHeaderFields()
                .entrySet()
                .forEach(System.out::println);


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseCode == 200 ? myURLConnection.getInputStream() : myURLConnection.getErrorStream()));

        String line;
        StringBuffer stringBuffer = new StringBuffer();

        while((line = bufferedReader.readLine()) != null){
            stringBuffer.append(line);
        }

        bufferedReader.close();

        JSONObject jsonObject = new JSONObject(stringBuffer.toString());
        System.out.println(jsonObject);


        return new RestMessage("responseCode : " + responseCode);
    }


    @GetMapping("/api/test")
    public RestMessage test(HttpServletRequest request) throws IOException, JSONException {

        return new RestMessage("test!!!");
    }


    @GetMapping(value = "/api/hello", produces = "application/json")
    public RestMessage helloUser(){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new RestMessage(String.format("Hello '%s'!", username));
    }

    @GetMapping("/api/admin")
    public RestMessage helloAdmin(Principal principal){

        return new RestMessage(String.format("Welcome '%s'", principal.getName()));
    }
}
