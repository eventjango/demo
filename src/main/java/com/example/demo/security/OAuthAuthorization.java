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
                .withClient("J3LgUqKshaDEm_NvK4gx").secret("SbEagQbVrd")
                .redirectUris("http://localhost:80/oauth-api/callback")
                .authorizedGrantTypes("authorization_code")
                .scopes("read", "write");
    }
}
