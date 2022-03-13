package com.example.containerapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static com.example.containerapp.model.User.ApplicationRole.*;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserAuthenticationProvider authProvider;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .antMatchers("/spring-security-rest/**").permitAll()
                .antMatchers("/api/users/**").hasAuthority(ADMIN.getAuthority())
                .antMatchers("/api/**").hasAnyAuthority(ADMIN.getAuthority(), USER.getAuthority())
                .and()
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable();
    }
}
