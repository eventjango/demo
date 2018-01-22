package com.example.demo.oauth2;


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

    private final static String antMatcher = "/api/";

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
                /*.anonymous().disable()*/
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()

                .antMatchers("/api/hello").access("hasAnyRole('USER')")
                .antMatchers("/api/admin").hasRole("ADMIN")
                .antMatchers("/api/test").access("hasAnyRole('USER', 'ANONYMOUS', 'ADMIN')")
                .antMatchers("/api/*").authenticated();/*.and().csrf().disable();*/
    }


    private static class OAuthRequestMatcher implements RequestMatcher {

        @Override
        public boolean matches(HttpServletRequest request) {

            String currentPath = request.getServletPath();
            return (currentPath.length() >= antMatcher.length())? currentPath.substring(0, antMatcher.length()).equals(antMatcher) : false;
        }
    }
}
