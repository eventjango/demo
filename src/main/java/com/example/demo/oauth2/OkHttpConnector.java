package com.example.demo.oauth2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class OkHttpConnector {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final OkHttpClient client = new OkHttpClient();

    public static Map<String, Object> getTokenApi(ClientInfo clientInfo){


        byte[] encoded = Base64.encodeBase64("trusted_app:secret".getBytes());
        byte[] decoded = Base64.decodeBase64(encoded);

        /*String clientCredentials = Base64.getEncoder().encode("trusted_app:secret".getBytes()).toString();
        String authorization = "Basic " + clientCredentials;*//*Base64.getDecoder().decode(clientCredentials.getBytes()).toString();*/

        String authorization = "Basic" + new String(decoded);

        try {

            HttpUrl.Builder urlBuilder = HttpUrl.parse(clientInfo.getTokenUrl()).newBuilder();
            urlBuilder.addQueryParameter("grant_type", clientInfo.getGrant());
            urlBuilder.addQueryParameter("username", clientInfo.getUsername());
            urlBuilder.addQueryParameter("password", clientInfo.getPassword());
            urlBuilder.build();

            Request request = new Request.Builder().url(clientInfo.getTokenUrl()).header("Authorization", authorization).build();
            Response response = client.newCall(request).execute();

            return new Gson().fromJson(response.body().string(), new TypeToken<Map<String, Object>>(){}.getType());

        }catch (IOException e){

            return new HashMap<>();
        }
    }

    public static Map<String,Object> getMap(String url) {
        try {
            Request request = new Request.Builder().url(url).header("User-Agent","Mozilla/5.0").build();
            Response response = client.newCall(request).execute();
            log.debug(response.toString());
            return new Gson().fromJson(response.body().string(), new TypeToken<Map<String, Object>>(){}.getType());
        } catch (IOException e){
            return new HashMap<>();
        }
    }

    public static String getString(String url) {
        try {
            Request request = new Request.Builder().url(url).header("User-Agent","Mozilla/5.0").build();
            Response response = client.newCall(request).execute();
            log.debug(response.toString());
            return StringUtils.defaultString(response.body().string());
        } catch (IOException e){
            return "";
        }
    }


    public static Map<String,Object> postMap(String url, Object obj)  {
        try {
            Gson gson = new Gson();
            RequestBody body = RequestBody.create(JSON, gson.toJson(obj));
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .header("User-Agent","Mozilla/5.0")
                    .build();
            Response response = client.newCall(request).execute();
            log.debug(response.toString());
            return new Gson().fromJson(response.body().string(), new TypeToken<Map<String, Object>>(){}.getType());

        } catch (IOException e){
            return new HashMap<>();
        }
    }

    public static String postString(String url, Object obj) {
        try {
            Gson gson = new Gson();
            RequestBody body = RequestBody.create(JSON, gson.toJson(obj));
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .header("User-Agent","Mozilla/5.0")
                    .build();
            Response response = client.newCall(request).execute();
            log.debug(response.toString());
            return StringUtils.defaultString(response.body().string());

        } catch (IOException e){
            return "";
        }
    }

    public static String postFormDataString(String url, Map<String,String>params) {
        try {

            FormBody.Builder formBody = new FormBody.Builder();
            for(String key : params.keySet()) {
                formBody = formBody.add(key, params.get(key));
            }
            RequestBody requestBody = formBody.build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .header("User-Agent","Mozilla/5.0")
                    .build();
            Response response = client.newCall(request).execute();
            log.debug(response.toString());
            return StringUtils.defaultString(response.body().string());

        } catch (IOException e){
            return "";
        }
    }

    public static Map<String,Object> postFormDataMap(String url, Map<String,String>params)  {
        try {
            FormBody.Builder formBody = new FormBody.Builder();
            for(String key : params.keySet()) {
                formBody = formBody.add(key, params.get(key));
            }
            RequestBody requestBody = formBody.build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .header("User-Agent","Mozilla/5.0")
                    .build();
            Response response = client.newCall(request).execute();
            log.debug(response.toString());
            return new Gson().fromJson(response.body().string(), new TypeToken<Map<String, Object>>(){}.getType());

        } catch (IOException e){
            return new HashMap<>();
        }
    }

}
