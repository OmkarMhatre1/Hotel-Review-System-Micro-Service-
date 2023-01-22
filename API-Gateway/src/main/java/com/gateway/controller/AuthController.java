package com.gateway.controller;

import com.gateway.model.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private Logger logger= LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(
           @RegisteredOAuth2AuthorizedClient ("okta") OAuth2AuthorizedClient client,
           @AuthenticationPrincipal OidcUser user,
           Model model
    ){

        logger.info("user email id :{}",user.getEmail());

        //creating auth response
        AuthResponse authResponse = new AuthResponse();
        //setting email to auth response
        authResponse.setUserId(user.getEmail());

        //access access_token to auth response --> OAuth2AuthorizedClient
        authResponse.setAccessToken(client.getAccessToken().getTokenValue());

        //access refresh_token to auth response --> OAuth2AuthorizedClient
        authResponse.setRefreshToken(client.getRefreshToken().getTokenValue());

        //access expireToken to auth response --> OAuth2AuthorizedClient
        authResponse.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());

        // removing authorities
        List<String> authorities = user.getAuthorities().stream().map(grantedAuthority -> {
            return grantedAuthority.getAuthority();
        }).collect(Collectors.toList());

        authResponse.setAuthorities(authorities);

        return new ResponseEntity<>(authResponse, HttpStatus.OK);

    }


}
