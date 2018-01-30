package com.example.demo.test;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class ClientInfo {

    private String id;

    private String secret;

    private String grantType;

    private String scope;

    private String redirect_uri;


    @Builder
    public ClientInfo(String id, String secret, String grantType, String scope, String redirect_uri){

        this.id = id;
        this.secret = secret;
        this.grantType = grantType;
        this.scope = scope;
        this.redirect_uri = redirect_uri;
    }
}
