/*
package com.example.demo.configurer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${resource.id:spring-boot-application}")
    private String resourceId;


    private static class OAuthRequestedMatcher implements RequestMatcher{

        @Override
        public boolean matches(HttpServletRequest request) {

            String auth = request.getHeader("Authorization");

            boolean haveOauth2Token = (auth != null) && (auth.startsWith("Bearer") || auth.startsWith("bearer"));
            boolean haveAccessToken = request.getParameter("access_token") != null;

            return (haveOauth2Token || haveAccessToken);
        }
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

        resources.resourceId(resourceId);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.requestMatcher(new OAuthRequestedMatcher())
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated();
    }
}
*/
