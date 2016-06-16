package ru.alfastrahoms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import java.security.Principal;

@SpringBootApplication
@Controller
@SessionAttributes("authorizationRequest")
@EnableResourceServer
public class AuthserverApplication extends WebMvcConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthserverApplication.class);

    @PostConstruct
    public void logSomething() {
        logger.debug("DebugDebugDebugDebugDebugDebugDebugDebugDebugDebugDebugDebugDebugDebug");
        logger.info("InfoInfoInfoInfoInfoInfoInfoInfoInfoInfoInfoInfoInfoInfoInfoInfoInfoInfo");
        logger.error("ErrorErrorErrorErrorErrorErrorErrorErrorErrorErrorErrorErrorErrorError");
        logger.trace("TraceTraceTraceTraceTraceTraceTraceTraceTraceTraceTraceTraceTraceTrace");
    }

    @RequestMapping("/user")
    @ResponseBody
    public Principal user(Principal user) {
        logger.info("test logger system. user {}", user);
        return user;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/oauth/confirm_access").setViewName("authorize");
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthserverApplication.class, args);
    }

}
