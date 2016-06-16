package ru.alfastrahoms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {

    @Autowired
    JwtTokenStore tokenStore;

    @PreAuthorize("hasAuthority('ROLE_TEST') OR hasRole('ADMIN')")
    @RequestMapping(path = "/resource", method = RequestMethod.GET)
    public Message home() {
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        Map<String, Object> map = tokenStore.readAccessToken(details.getTokenValue()).getAdditionalInformation();
        System.out.println(map);
        return new Message(authentication.toString() + "<br>" + map.toString());
    }

    @RequestMapping(path = "/foo", method = RequestMethod.GET)
    public Message readFoo() {
        return new Message("read foo");
    }

    @RequestMapping(path = "/foo", method = RequestMethod.POST)
    public Message writeFoo() {
        return new Message("write foo");
    }

}
