package com.example.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint;

import javax.annotation.PostConstruct;

@Configuration
@EnableAuthorizationServer
public class OAuth extends AuthorizationServerConfigurerAdapter{


    /*@Autowired
    private AuthorizationEndpoint authorizationEndpoint;

    @PostConstruct
    public void initialize(){

        authorizationEndpoint.setUserApprovalPage("forward:/oauth/custom_confirm_access");
        authorizationEndpoint.setErrorPage("forward:/oauth/confirm_error");
    }*/

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()
                .withClient("clientapp").secret("123456")
                /*.redirectUris("http://localhost:80/callback")*/
                .authorizedGrantTypes("authorization_code", "password", "refresh_token", "client_credentials", "implicit")
                .scopes("read_profile", "read_contact");
    }

}
