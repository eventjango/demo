package com.example.demo.oauth2;

import lombok.Data;

@Data
public class ClientInfo {

    private String id;

    private String username;

    private String tokenUrl;

    private String password;

    private String grant;

    private String secret;

    private String accessToken;

    private String refreshToken;

    private String expiresIN;
}
