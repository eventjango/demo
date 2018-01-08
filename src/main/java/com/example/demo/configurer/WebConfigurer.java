package com.example.demo.configurer;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

@Configuration
public class WebConfigurer extends WebMvcConfigurerAdapter{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        WebContentInterceptor webContentInterceptor = new WebContentInterceptor();

        webContentInterceptor.setCacheSeconds(0);
        webContentInterceptor.setUseExpiresHeader(true);
        webContentInterceptor.setUseCacheControlHeader(true);
        webContentInterceptor.setUseCacheControlNoStore(true);

        super.addInterceptors(registry);
    }
}
