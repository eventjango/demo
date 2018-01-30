package com.example.demo.test;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class AuthorizationCodeInfo {

    private String code;

    private String state;

    private String tokenUrl;

    private ClientInfo clientInfo;


    @Builder
    public AuthorizationCodeInfo(String code, String state, String tokenUrl, ClientInfo clientInfo){

        this.code = code;
        this.state = state;
        this.tokenUrl = tokenUrl;
        this.clientInfo = clientInfo;
    }
}
