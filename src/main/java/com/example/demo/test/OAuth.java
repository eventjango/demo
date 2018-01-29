package com.example.demo.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class OAuth extends AuthorizationServerConfigurerAdapter{

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()
                .withClient("clientapp").secret("123456")
                /*.redirectUris("http://localhost:80/callback")*/
                .authorizedGrantTypes("authorization_code", "password", "refresh_token", "client_credentials", "implicit")
                .scopes("read_profile", "read_contact");
    }
}
