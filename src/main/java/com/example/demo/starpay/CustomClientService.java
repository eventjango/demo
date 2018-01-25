/*
package com.example.demo.starpay;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CustomClientService implements ClientDetailsService {

    @Override
    public ClientDetails loadClientByClientId(String client) throws ClientRegistrationException {

        BaseClientDetails details = new BaseClientDetails();
        details.setClientId("trusted_app");
        details.setAuthorizedGrantTypes(Arrays.asList("client_credentials", "password", "refresh_token", "implicit", "authorization_code"));
        details.setScope(Arrays.asList("read", "write", "trust"));
        details.setResourceIds(Arrays.asList("app_info"));
        details.setClientSecret("secret");


        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_TRUSTED_CLIENT"));

        details.setAuthorities(authorities);
        details.setAccessTokenValiditySeconds(10000);
        details.setRefreshTokenValiditySeconds(30000);

        return details;
    }
}
*/
