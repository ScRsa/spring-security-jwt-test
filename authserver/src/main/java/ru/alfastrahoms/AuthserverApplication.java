package ru.alfastrahoms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
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
@EnableDiscoveryClient
public class AuthserverApplication extends WebMvcConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthserverApplication.class);

    @Value("${spring.cloud.consul.host}")
    String host;

    @Value("${spring.cloud.consul.port}")
    String port;

    @Value("${spring.cloud.consul.discovery.prefer-ip-address}")
    String preferIp;

    @Value("${spring.cloud.consul.discovery.healthCheckPath}")
    String healthCheckPath;

    @PostConstruct
    public void logSomething() {
        logger.debug("DebugDebugDebugDebugDebugDebugDebugDebugDebugDebugDebugDebugDebugDebug");
        logger.info("InfoInfoInfoInfoInfoInfoInfoInfoInfoInfoInfoInfoInfoInfoInfoInfoInfoInfo");
        logger.error("ErrorErrorErrorErrorErrorErrorErrorErrorErrorErrorErrorErrorErrorError");
        logger.trace("TraceTraceTraceTraceTraceTraceTraceTraceTraceTraceTraceTraceTraceTrace");
        logger.debug("host {}; port {}; preferIp {}; healthCheckPath {}", host, port, preferIp, healthCheckPath);
    }

    @RequestMapping("/user")
    @ResponseBody
    public Principal user(Principal user) {
        logger.info("Запрос информации пользователя: user-name {}, user-info {}", user.getName(), user);
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
