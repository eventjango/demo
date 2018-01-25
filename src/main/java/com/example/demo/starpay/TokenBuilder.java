/*
package com.example.demo.starpay;

import artec.starapp.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class TokenBuilder {


    public static JSONObject build(Member member) throws IOException, JSONException {

        String username = member.getEmail();
        String password = member.getPassword();

        username = password = "admin";

        String tokenUrl = "http://localhost";
        tokenUrl += ":8080";
        tokenUrl += "/oauth/token";

        String parameters = "grant_type=" + "password";
        parameters += "&username=" + username;
        parameters += "&password=" + password;

        HttpURLConnection connection = (HttpURLConnection) new URL(tokenUrl).openConnection();
        String userCredential = "trusted_app:secret";
        String basicAuth = "Basic " + new String(new Base64().encode(userCredential.getBytes()));

        connection.setRequestProperty("Authorization", basicAuth);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Language", "en-US");
        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);

        connection.connect();

        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(parameters);
        out.flush();
        out.close();

        int responseCode = connection.getResponseCode();

        log.debug("URL : " + tokenUrl);
        log.debug("Method : " + connection.getRequestMethod());
        log.debug("Post parameters : " + parameters);
        log.debug("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(responseCode == 200 ? connection.getInputStream() : connection.getErrorStream()));

        String line;
        StringBuffer stringBuffer = new StringBuffer();

        while ((line = in.readLine()) != null){
            stringBuffer.append(line);
        }

        in.close();

        return new JSONObject(stringBuffer.toString());
    }
}
*/
