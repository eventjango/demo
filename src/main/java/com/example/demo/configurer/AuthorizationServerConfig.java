/*
package com.example.demo.configurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

    @Value("${resource.id:spring-boot-application}")
    private String resourceId;

    @Value("${access_token.validity_period:3600}")
    private int accessTokenValiditySeconds = 3600;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        final JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        */
/*jwtAccessTokenConverter.setSigningKey("secret");*//*

        return jwtAccessTokenConverter;
    }

    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints
                .authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

        security
                .tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')")
                .checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()

                .withClient("foo")
                    .secret("bar")
                    .authorizedGrantTypes("authorization_code", "implicit", "password")
                    .scopes("read", "write")
                    .resourceIds(resourceId)
                    .accessTokenValiditySeconds(accessTokenValiditySeconds)
        .and()

                .withClient("normal-app")
                    .authorizedGrantTypes("authorization_code", "implicit")
                    .authorities("ROLE_CLIENT")
                    .scopes("read", "write")
                    .resourceIds(resourceId)
                    .accessTokenValiditySeconds(accessTokenValiditySeconds)

        .and()

                .withClient("trusted-app")
                    .authorizedGrantTypes("client_credentials", "password")
                    .authorities("ROLE_TRUSTED_CLIENT")
                    .scopes("read", "write")
                    .resourceIds(resourceId)
                    .accessTokenValiditySeconds(accessTokenValiditySeconds)
                    .secret("secret");

    }
}
*/
