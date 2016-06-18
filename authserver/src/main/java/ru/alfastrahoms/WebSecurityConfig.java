package ru.alfastrahoms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(-20)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .csrf().disable()
          .formLogin().loginPage("/login").permitAll()
          .and().requestMatchers().antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access", "/admin/health")
          .and().authorizeRequests().antMatchers("/admin/health").permitAll()
          .and().authorizeRequests().anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
          .inMemoryAuthentication()
          .withUser("qwe").password("qwe").roles("USER").and()
          .withUser("test").password("test").roles("USER", "TEST", "ADMIN").and()
          .withUser("admin").password("admin").roles("USER", "ADMIN");

    }
}
