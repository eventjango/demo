package com.example.demo.test;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Controller
public class UserController {

    @GetMapping("/login")
    public String login(){

        String authorizeUrl = "http://localhost";
        authorizeUrl += ":80";
        authorizeUrl += "/oauth/authorize?";
        authorizeUrl += "client_id=".concat("naver");
        authorizeUrl += "&redirect_uri=".concat("http://localhost:80/callback");
        authorizeUrl += "&response_type=code";
        authorizeUrl += "&scope=".concat("read_profile");

        SecureRandom secureRandom = new SecureRandom();
        String state = new BigInteger(130, secureRandom).toString();

        state = new String(new Base64().encode(state.getBytes()));

        authorizeUrl += "&state=".concat(state);

        return "redirect:".concat(authorizeUrl);
    }


    @GetMapping("/callback")
    public String callback(HttpServletRequest request, Model model) throws IOException, JSONException{

        String errorCode = request.getParameter("error");

        if(Objects.nonNull(errorCode)){
            if(errorCode.equals("access_denied")) return "";
        }

        ClientInfo clientInfo = ClientInfo.builder()
                .id("naver")
                .secret("J3LgUqKshaDEm_NvK4gx")
                .grantType("authorization_code")
                .scope("read_profile")
                .redirect_uri("http://localhost:80/callback")
                .build();


        AuthorizationCodeInfo authorizationCodeInfo = AuthorizationCodeInfo.builder()
                .clientInfo(clientInfo)
                .code(request.getParameter("code"))
                .state(request.getParameter("state"))
                .tokenUrl("http://localhost:80/oauth/token")
                .build();


        JSONObject tokenInfo = AuthorizationCodeTokenBuilder.builder().authorizationCodeInfo(authorizationCodeInfo).build();

        log.debug("responseCode : " + tokenInfo.get("responseCode").toString());
        log.debug("tokenUrl : " + tokenInfo.get("tokenUrl").toString());
        log.debug("accessToken : " + tokenInfo.getString("access_token"));
        log.debug("refreshToken : " + tokenInfo.getString("refresh_token"));


        HttpSession session = request.getSession();

        session.setAttribute("access_token", tokenInfo.getString("access_token"));
        session.setAttribute("token_type", tokenInfo.getString("token_type"));
        session.setAttribute("refresh_token", tokenInfo.getString("refresh_token"));
        session.setAttribute("expires_in", tokenInfo.getString("expires_in"));

        String authorization
                = session.getAttribute("token_type")
                .toString()
                .concat(" ")
                .concat(session.getAttribute("access_token").toString());

        Map<String, Object> headerMap
                = new HashMap<String, Object>()
        {
            {
                this.put("authorization", authorization);
            }
        };


        Set<Map.Entry<String, Object>> headerSet = headerMap.entrySet();
        Map.Entry<String, Object> header = headerSet.stream().findFirst().get();

        String api = "http://localhost:80";
        api += "/api/profile";

        HttpURLConnection urlConnection = (HttpURLConnection) new URL(api).openConnection();

        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty(header.getKey(), header.getValue().toString());

        int responseCode = urlConnection.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(responseCode == 200? urlConnection.getInputStream() : urlConnection.getErrorStream()));

        String line;
        StringBuffer stringBuffer = new StringBuffer();

        while ((line = in.readLine()) != null){
            stringBuffer.append(line);
        }

        in.close();

        JSONObject json = new JSONObject(stringBuffer.toString());
        model.addAttribute("user", json.toString());

        log.debug(json.toString());

        return "forward:".concat("/result");

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
