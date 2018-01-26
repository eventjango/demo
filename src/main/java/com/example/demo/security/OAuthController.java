package com.example.demo.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/oauth-api")
public class OAuthController {

    @Value("oauth.megazone.clinetId")
    private String clientIdAndMegazone = "clientIdAndMegazone";


    @GetMapping("/login/{type}")
    public String login(@PathVariable("type") String type, HttpServletRequest request){

        switch (type){

            case OAuthClientsFactory.MEGA_ZONE:

                String clinetId = clientIdAndMegazone;
                String redirectURI;

        }

    }
}
