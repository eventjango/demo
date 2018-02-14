package com.example.demo.oauth2;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class JoinClaimsTokenEnhancer implements TokenEnhancer{


    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        /*MemberDetails memberDetails = MemberDetailsHelper.getMemberDetails();*/

        String username = authentication.getName();

        System.out.println("username : " + username);

        DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;

        defaultOAuth2AccessToken
                .setAdditionalInformation(
                        new HashMap<String, Object>()
                        {
                            {
                                this.put("user_name", username);
                                this.put("test", "이지은");
                            }
                        }
                );

        return defaultOAuth2AccessToken;
    }
}
