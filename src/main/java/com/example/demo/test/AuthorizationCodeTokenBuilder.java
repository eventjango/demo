package com.example.demo.test;


import lombok.Builder;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class AuthorizationCodeTokenBuilder {


    @Builder
    public static JSONObject build(AuthorizationCodeInfo authorizationCodeInfo) throws IOException, JSONException{

        String code = authorizationCodeInfo.getCode();
        String state = authorizationCodeInfo.getState();
        String tokenUrl = authorizationCodeInfo.getTokenUrl();

        String params = "code=".concat(code);

        params += "&grant_type=".concat(authorizationCodeInfo.getClientInfo().getGrantType());
        params += "&client_id=".concat(authorizationCodeInfo.getClientInfo().getId());
        params += "&client_secret=".concat(authorizationCodeInfo.getClientInfo().getSecret());
        params += "&scope=".concat(authorizationCodeInfo.getClientInfo().getScope());
        params += "&redirect_uri=".concat(authorizationCodeInfo.getClientInfo().getRedirect_uri());

        HttpURLConnection urlConnection = (HttpURLConnection) new URL(tokenUrl).openConnection();

        String userCredential = authorizationCodeInfo.getClientInfo().getId() + ":" + authorizationCodeInfo.getClientInfo().getSecret();
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

        BufferedReader in = new BufferedReader(new InputStreamReader(responseCode == 200? urlConnection.getInputStream() : urlConnection.getErrorStream()));

        String line;
        StringBuffer stringBuffer = new StringBuffer();

        while ((line = in.readLine()) != null){
            stringBuffer.append(line);
        }

        in.close();

        JSONObject result = new JSONObject(stringBuffer.toString());

        result.put("responseCode", responseCode);
        result.put("tokenUrl", tokenUrl);

        return result;
    }

}
