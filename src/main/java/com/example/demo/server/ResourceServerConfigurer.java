/*
package com.example.demo.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {


    public static final String RESOURCE_ID = "my_rest_api";


    @Override
    public void configure(ResourceServerSecurityConfigurer resources){

        resources.resourceId(RESOURCE_ID).stateless(false);
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .anonymous().disable()
                .requestMatchers().antMatchers("/user")
                .and()

                .antMatcher("/user")
                .authorizeRequests()
                */
/*.antMatchers("/user/**").access("hasRole('ADMIN')")*//*

                .antMatchers("/user").authenticated()
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());

        */
/*http
        .authorizeRequests()
        .antMatchers("/user/**")
        .hasRole("ADMIN");*//*


    }
}
*/
