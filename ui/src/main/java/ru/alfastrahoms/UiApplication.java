package ru.alfastrahoms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableZuulProxy
@EnableOAuth2Sso
public class UiApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(UiApplication.class, args);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		  .csrf().disable()
		  .logout().and().antMatcher("/**").authorizeRequests()
		  .antMatchers("/index.html", "/home.html", "/", "/login").permitAll()
		  .anyRequest().authenticated();
	}

}

