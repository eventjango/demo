package com.example.demo.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class OAuthAuthorization  extends AuthorizationServerConfigurerAdapter{


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()
                .withClient("client_app").secret("123456")
                .redirectUris("http://localhost:80/callback")
                .authorizedGrantTypes("authorization_code")
                .scopes("read", "write");
    }
}
