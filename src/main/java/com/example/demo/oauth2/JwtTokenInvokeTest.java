/*
package com.example.demo.oauth2;


import artec.base.oauth2.AuthorizationCodeInfo;
import artec.base.oauth2.AuthorizationCodeTokenBuilder;
import artec.base.oauth2.ClientInfo;
import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;

public class JwtTokenInvokeTest {


    private String code = "1Q7OOj";

    private String clientId = "2oHNrjK0kJQaJk5AhiPOIt";
    private String clientSecret = "iulove";
    private String redirectUri = "http://localhost:8080/oauth-api/result-code";
    private String tokenUrl = "http://localhost:8080/oauth/token";
    private String grant = "authorization_code";
    private String scope = "read";

    @Test
    public void apply() throws IOException, JSONException {

        AuthorizationCodeInfo authorizationCodeInfo = AuthorizationCodeInfo.builder()
                .code(code)
                .clientInfo(
                        ClientInfo.builder()
                        .id(clientId)
                        .secret(clientSecret)
                        .grantType(grant)
                        .redirect_uri(redirectUri)
                        .scope(scope)
                        .build())
                .tokenUrl(tokenUrl)
                .state("state")
                .build();

        AuthorizationCodeTokenBuilder
                .builder()
                .authorizationCodeInfo(authorizationCodeInfo)
                .build()

        .entrySet()
        .forEach(System.out::println);
    }

}
*/
