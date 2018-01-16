package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
public class DemoApplication {

	public static void main(String[] args) {
		/*SpringApplication.run(DemoApplication.class, args);*/
		SpringApplication.run(new Class[]{DemoApplication.class, ServletInitializer.class}, args);
	}
}
