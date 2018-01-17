/*
package com.example.demo.oauth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableResourceServer
public class ResourceConfigurer extends ResourceServerConfigurerAdapter{

    @Value("${security.oauth2.resource.id}")
    private String resourceId;


    @Autowired
    private DefaultTokenServices tokenServices;


    @Autowired
    private TokenStore tokenStore;


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

        resources
                .resourceId(resourceId)
                .tokenServices(tokenServices)
                .tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .requestMatcher(new OAuthRequestMatcher())
                .csrf().disable()
                .anonymous().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()

                .antMatchers("/user/**").access("hasAnyRole('USER', 'ADMIN')");

    }


    private static class OAuthRequestMatcher implements RequestMatcher{

        @Override
        public boolean matches(HttpServletRequest request) {

            // Determine if the resource called is "/user/**"

            String path = request.getServletPath();

            if(path.length() >= 5){
                path = path.substring(0, 5);
                boolean isRight = path.equals("/user/");
                return isRight;
            }
            else return false;
        }
    }
}
*/
